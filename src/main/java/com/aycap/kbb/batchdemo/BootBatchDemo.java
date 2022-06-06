package com.aycap.kbb.batchdemo;

import com.aycap.kbb.batchdemo.model.ApplicationModel;
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
import java.util.Map;

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
    AppReader appReader;

    @PostMapping("/post")
    public Object handle(@RequestBody List<ApplicationModel> application) throws Exception {
        Long bKey = System.currentTimeMillis();
        appReader.setApplicationMap(bKey,application);
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .addLong("batch-key", bKey)
                .toJobParameters();
        jobLauncher.run(job, jobParameters);
        Map<String,Object> response = new HashMap<>();
        List<Object> resultErrors = appReader.getResult(bKey);

        response.put("success_count",application.size()-resultErrors.size());
        response.put("error_count",resultErrors.size());
        response.put("errors",resultErrors);

        return response;
    }
}
