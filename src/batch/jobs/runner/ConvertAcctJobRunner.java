package batch.jobs.runner;

import batch.jobs.configuration.JobsConfigurationFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConvertAcctJobRunner {

    private static String jobName = "convertAcctJob";

    public static void main(String[] args) {

        AnnotationConfigApplicationContext jobContext = new AnnotationConfigApplicationContext(
                JobsConfigurationFactory.getJobConfiguration(jobName));

        //Put JobName to JobParameters
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("jobName", jobName)
                .toJobParameters();

        Job job = (Job) jobContext.getBean(jobName);

        //Run job with jobParameters
        JobLauncher jobLauncher = (JobLauncher) jobContext.getBean("jobLauncher");


        try {
            JobExecution je = jobLauncher.run(job, jobParameters);

            if (je.getExitStatus().getExitCode().contentEquals("COMPLETED")) {
                System.exit(999);
            } else {
                System.exit(0);
            }
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
            | JobParametersInvalidException e) {
            e.printStackTrace();
        }

    }
}
