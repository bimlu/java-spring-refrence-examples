package com.mostafa.controller;

import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class JobController {

    private JobLauncher jobLauncher; // Used to launch a job.
    private Job job; //initialized by job bean of SpringBatchConfig class.


    /**
     * Trigger Spring Batch Job to save data csv to mysql
     */
    @PostMapping("/save")
    public void saveDataCsvToDBJob() {
        //Store current system time milliseconds at batch_job_execution_params table
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("start-at", System.currentTimeMillis()).toJobParameters();
        try {
            //Launch job
            jobLauncher.run(job, jobParameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
