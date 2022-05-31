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
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@SpringBootApplication
@RestController
@RequestMapping("/invoke-job/")
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

    @Autowired
    BodyReader bodyReader;

    @GetMapping("get/{size}")
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

    @PostMapping("/post")
    public String handle(@RequestBody List<HashMap<String,Object>> application) throws Exception {
        Long bKey = System.currentTimeMillis();
        bodyReader.setPersons(bKey,application);
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .addLong("batch-key", bKey)
                .toJobParameters();
        jobLauncher.run(job, jobParameters);

        List<String> result = bodyReader.getResult(bKey);
        log.info(String.valueOf(result));

        return "error : " + result;
    }
}
