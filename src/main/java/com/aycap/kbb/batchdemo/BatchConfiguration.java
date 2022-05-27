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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Slf4j
@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

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
            ListItemReader<Person> itemReader) {
        return stepBuilderFactory.get("step1")
                .<Person, Person>chunk(1000)
                .reader(itemReader)
                .processor(processor())
                .writer(writer)
                .build();
    }

    @StepScope
    @Bean
    public ListItemReader<Person> itemReader(
            MyListReader myListReader,
            @Value("#{jobParameters['batch-key']}") Long batchKey
    ) {
        return myListReader.getPersons(batchKey);
    }

    @Bean
    ItemProcessor<Person, Person> processor() {
        return person -> {
            final String firstName = person.getFirstName().toUpperCase();
            final String lastName = person.getLastName().toUpperCase();
            return new Person(firstName, lastName);
        };
    }

    @Bean
    public JdbcBatchItemWriter<Person> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Person>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO people (first_name, last_name) VALUES (:firstName, :lastName)")
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public JobExecutionListener jobExecutionListener(JdbcTemplate jdbcTemplate) {
        return new JobExecutionListener() {

            @Override
            public void beforeJob(JobExecution jobExecution) {
                log.info("Job : " + jobExecution.getJobConfigurationName() + " started!");
            }

            @Override
            public void afterJob(JobExecution jobExecution) {
                if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
                    log.info("!!! JOB FINISHED! Time to verify the results");
                    int size = jdbcTemplate.query("SELECT first_name, last_name FROM people",
                            (rs, row) -> new Person(
                                    rs.getString(1),
                                    rs.getString(2))
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
