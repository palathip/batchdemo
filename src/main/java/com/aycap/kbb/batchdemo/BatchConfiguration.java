package com.aycap.kbb.batchdemo;

import com.aycap.kbb.batchdemo.model.Application;
import com.aycap.kbb.batchdemo.model.ApplicationModel;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    AppReader appReader;

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
            JdbcBatchItemWriter<Application> writer,
            ListItemReader<ApplicationModel> itemReader,
            ItemProcessor<ApplicationModel,Application> processor) {
        return stepBuilderFactory.get("step1")
                .<ApplicationModel, Application>chunk(1000)
                .reader(itemReader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @StepScope
    @Bean
    public ListItemReader<ApplicationModel> itemReader(
            AppReader appReader,
            @Value("#{jobParameters['batch-key']}") Long batchKey
    ) {
        return appReader.getApplicationMap(batchKey);
    }

    @Autowired
    Validator validator;

    @Bean
    ItemProcessor<ApplicationModel, Application> processor() {
        return applicationModel -> {

              final ObjectMapper mapper = new ObjectMapper();
              Application application = mapper.convertValue(applicationModel,Application.class);

//  region custom validator
//  ref1 : https://www.baeldung.com/javax-validation#2-validate-the-bean
//  ref2: https://reflectoring.io/bean-validation-with-spring-boot/#validating-programmatically

            Set<ConstraintViolation<Application>> violations = validator.validate(application);
            if(!violations.isEmpty()) {
                HashMap<String, String> resultObject = new HashMap<>();
                for (ConstraintViolation<Application> violation : violations) {
                    resultObject.put("application_no", application.getApplicationNo());
                    resultObject.put("reason", violation.getMessageTemplate());
                    objectResult.add(resultObject);
                }
                return null;
            }
//  endregion

            return application;
        };
    }

    @Bean
    public JdbcBatchItemWriter<Application> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Application>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO application " +
                        "(application_no," +
                        "product_code," +
                        "sum_insured," +
                        "net_premium," +
                        "gross_premium," +
                        "effective_date," +
                        "expired_date," +
                        "payer_title_name," +
                        "payer_title_name_en," +
                        "payer_name," +
                        "payer_name_en," +
                        "payer_lastname," +
                        "payer_lastname_en," +
                        "payer_birth_date) " +
                        "VALUES " +
                        "(:applicationNo," +
                        ":productCode," +
                        ":sumInsured," +
                        ":netPremium," +
                        ":grossPremium," +
                        ":effectiveDate," +
                        ":expiredDate," +
                        ":payerTitleName," +
                        ":payerTitleNameEn," +
                        ":payerName," +
                        ":payerNameEn," +
                        ":payerLastname," +
                        ":payerLastnameEn," +
                        ":payerBirthDate) " +
                        "")
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
                appReader.setResult(key.getLong("batch-key"),objectResult);
                objectResult.clear();
                log.info("Job : " + jobExecution.getJobConfigurationName() + " started!");
            }

            @Override
            public void afterJob(JobExecution jobExecution) {
                if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
                    log.info("!!! JOB FINISHED! Time to verify the results");
                    int size = jdbcTemplate.query("SELECT application_no FROM application",
                            (rs, row) -> new Application()).size();
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
    public FlatFileItemReader<Application> reader() {
        BeanWrapperFieldSetMapper<Application> mapper = new BeanWrapperFieldSetMapper<>();
        mapper.setTargetType(Application.class);
        String resourcePath = "sample-data.csv";//"sample-data.large.csv"
        return new FlatFileItemReaderBuilder<Application>()
                .name("personItemReader")
                .resource(new ClassPathResource(resourcePath))
                .delimited()
                .names("firstName", "lastName")
                .fieldSetMapper(mapper)
                .build();
    }
}
