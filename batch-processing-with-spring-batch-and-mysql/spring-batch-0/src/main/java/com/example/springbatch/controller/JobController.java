package com.example.springbatch.controller;

import com.example.springbatch.request.JobParamsRequest;
import com.example.springbatch.service.JobService;
import org.springframework.batch.core.launch.JobExecutionNotRunningException;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.NoSuchJobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/job")
public class JobController {

    @Autowired
    private JobService jobService;

    @Autowired
    JobOperator jobOperator;

    @PostMapping("start/{jobName}")
    public String startJob(@PathVariable("jobName") String jobName,
                           @RequestBody List<JobParamsRequest> jobParamsRequestList) {

        System.out.println(jobParamsRequestList);
        jobService.startJob(jobName, jobParamsRequestList);
        return "Job Started....";
    }

    @GetMapping("/stop/{jobExecutionId}")
    public String stopJob(@PathVariable long jobExecutionId) {

        try {
            jobOperator.stop(jobExecutionId);
        } catch (NoSuchJobExecutionException | JobExecutionNotRunningException e) {
            throw new RuntimeException(e);
        }
        return "Job stopped...";
    }
}
