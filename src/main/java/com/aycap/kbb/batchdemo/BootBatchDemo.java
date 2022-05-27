package com.aycap.kbb.batchdemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@SpringBootApplication
@RestController
public class BootBatchDemo {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(BootBatchDemo.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job job;

    @Autowired
    MyListReader myListReader;

    @RequestMapping("/invoke-job/{size}")
    public String handle(@PathVariable Integer size) throws Exception {
        Long bKey = System.currentTimeMillis();
        myListReader.setPersons(bKey, size);
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .addLong("batch-key", bKey)
                .toJobParameters();
        JobExecution x = jobLauncher.run(job, jobParameters);
        return "Batch job has been invoked :" + x.getJobConfigurationName();
    }
}
