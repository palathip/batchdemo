package com.aycap.kbb.batchdemo;

import com.aycap.kbb.batchdemo.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.annotation.Validated;

import javax.sql.DataSource;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.*;

@Slf4j
@Configuration
@Validated
@EnableBatchProcessing
public class BatchConfiguration {

    private final ArrayList<Object> objectResult = new ArrayList<>();

    @Autowired
    BodyReader bodyReader;

    @Bean
    public Job importUserJob(
            JobBuilderFactory jobBuilderFactory,
            JobExecutionListener jobExecutionListener,
            Step stepOne
    ) {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(jobExecutionListener)
                .flow(stepOne)
                .end()
                .build();
    }

    @Bean("stepOne")
    public Step step1(
            StepBuilderFactory stepBuilderFactory,
            JdbcBatchItemWriter<Person> writer,
            ListItemReader<Person> itemReader,
            ItemProcessor<Person, Person> processor) {
        return stepBuilderFactory.get("step1")
                .<Person, Person>chunk(1000)
                .reader(itemReader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @StepScope
    @Bean
    public ListItemReader<Person> itemReader(
            BodyReader bodyReader,
            @Value("#{jobParameters['batch-key']}") Long batchKey
    ) {
        return bodyReader.getPersons(batchKey);
    }

    @Autowired
    Validator validator;

    @Bean
    @StepScope
    ItemProcessor<Person, Person> processor(BodyReader bodyReader,
                                            @Value("#{jobParameters['batch-key']}") Long bKey) {
        return person -> {
            final String applicationNo = person.getApplicationNo().toUpperCase();
            final String firstName = person.getFirstName().toUpperCase();
            final String lastName = person.getLastName().toUpperCase();

//  region custom validator
//  ref1 : https://www.baeldung.com/javax-validation#2-validate-the-bean
//  ref2: https://reflectoring.io/bean-validation-with-spring-boot/#validating-programmatically

            Person user = new Person();
            user.setApplicationNo(applicationNo);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            Set<ConstraintViolation<Person>> violations = validator.validate(user);
            if(!violations.isEmpty()) {
                HashMap<String, String> resultObject = new HashMap<>();
                for (ConstraintViolation<Person> violation : violations) {
                    resultObject.put("application_no", applicationNo);
                    resultObject.put("reason", violation.getMessage());
                    objectResult.add(resultObject);
                }
                return null;
            }

//  endregion
            return new Person(applicationNo,firstName, lastName);
        };
    }

    @Bean
    public JdbcBatchItemWriter<Person> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Person>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO people (application_no,first_name, last_name) VALUES (:applicationNo, :firstName, :lastName)")
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public JobExecutionListener jobExecutionListener(JdbcTemplate jdbcTemplate) {
        return new JobExecutionListener() {

            @Override
            public void beforeJob(JobExecution jobExecution) {
                JobParameters key = jobExecution.getJobParameters();
                key.getLong("batch-key");
                bodyReader.setResult(key.getLong("batch-key"),objectResult);
                objectResult.clear();
                log.info("Job : " + jobExecution.getJobConfigurationName() + " started!");
            }

            @Override
            public void afterJob(JobExecution jobExecution) {
                if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
                    log.info("!!! JOB FINISHED! Time to verify the results");
                    int size = jdbcTemplate.query("SELECT application_no,first_name, last_name FROM people",
                            (rs, row) -> new Person(
                                    rs.getString(1),
                                    rs.getString(2),
                                    rs.getString(3))
                    ).size();
                    log.info("Found " + size + " items in the database.");

                    Long batchKey = jobExecution.getJobParameters().getLong("batch-key");
                    log.info(batchKey + " success");
                }
            }
        };
    }

    /**
     * Depreciated
     */
    public FlatFileItemReader<Person> reader() {
        BeanWrapperFieldSetMapper<Person> mapper = new BeanWrapperFieldSetMapper<>();
        mapper.setTargetType(Person.class);
        String resourcePath = "sample-data.csv";//"sample-data.large.csv"
        return new FlatFileItemReaderBuilder<Person>()
                .name("personItemReader")
                .resource(new ClassPathResource(resourcePath))
                .delimited()
                .names("firstName", "lastName")
                .fieldSetMapper(mapper)
                .build();
    }
}
