package batch.jobs.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class ConvAcctJobConfig {

    @Autowired
    StepBuilderFactory stepBuilder;

    @Autowired
    JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job convertAcctJob() {

        Step convAcctstep = stepBuilder.get("step")
                                .chunk(10)
                                .build();

        Job job = jobBuilderFactory.get("job")
                                .start(convAcctstep)
                                .build();

        return job;
    }

}
