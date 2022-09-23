package com.mostafa.config;

import com.mostafa.entity.User;
import com.mostafa.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class SpringBatchConfig {

    private JobBuilderFactory jobBuilderFactory;
    private StepBuilderFactory stepBuilderFactory;
    private UserRepository userRepository;

    /**
     * Read CSV and map csv columns with model.
     * @return reader object with model.
     */
    @Bean
    public FlatFileItemReader<User> reader() {
        FlatFileItemReader<User> itemReader = new FlatFileItemReader<>();
        itemReader.setResource(new FileSystemResource("src/main/resources/user.csv"));
        itemReader.setName("reader");
        itemReader.setLinesToSkip(1); //skip first line which is header.
        itemReader.setLineMapper(lineMapper()); //called mapper method to map csv to db.
        return itemReader;
    }

    /**
     * Map csv delimiter and map csv columns with model.
     * @return mapped model
     */
    private LineMapper<User> lineMapper() {
        DefaultLineMapper<User> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(","); //CSV delimiter
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("id", "firstName", "lastName","email", "gender", "contactNo",
                "country", "dob"); //Specify csv columns.

        BeanWrapperFieldSetMapper<User> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(User.class); //Specify field mapper model

        lineMapper.setLineTokenizer(lineTokenizer); // mapped csv
        lineMapper.setFieldSetMapper(fieldSetMapper); // mapped model
        return lineMapper;
    }

    /**
     * Process data before write in db.
     * @return processor with process data
     */
    @Bean
    public UserProcessor processor() {
        return new UserProcessor();
    }

    /**
     * Write data in db
     * @return writer object
     */
    @Bean
    public RepositoryItemWriter<User> writer() {
        RepositoryItemWriter<User> writer = new RepositoryItemWriter<>();
        writer.setRepository(userRepository);
        writer.setMethodName("save");
        return writer;
    }

    /**
     * Build job step using StepBuilderFactory with reader (CSV), processor (if any),
     * writer (in DB) and AsyncTaskExecutor
     * @return built step
     */
    @Bean
    public Step step() {
        return stepBuilderFactory.get("step").<User, User>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .taskExecutor(taskExecutor())
                .build();
    }

    /**
     * Build job bean using JobBuilderFactory using job step or steps.
     * @return built job.
     */
    @Bean
    public Job job() {
        return jobBuilderFactory.get("save-user")
                .flow(step())
//                .next(step())
                .end().build();
    }

    /**
     * User to insert concurrently
     * @return Concurrent TaskExecutor
     */
    @Bean
    public TaskExecutor taskExecutor() {
        SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor();
        asyncTaskExecutor.setConcurrencyLimit(10);
        return asyncTaskExecutor;
    }

}
