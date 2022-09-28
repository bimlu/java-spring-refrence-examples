package com.example.springbatch.config;

import com.example.springbatch.listener.SkipListener;
import com.example.springbatch.listener.SkipListenerImpl;
import com.example.springbatch.model.StudentCsv;
import com.example.springbatch.model.StudentJdbc;
import com.example.springbatch.model.StudentJson;
import com.example.springbatch.model.StudentXml;
import com.example.springbatch.processor.FirstItemProcessor;
import com.example.springbatch.reader.FirstItemReader;
import com.example.springbatch.writer.FirstItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.skip.AlwaysSkipItemSkipPolicy;
import org.springframework.batch.item.database.*;
import org.springframework.batch.item.file.*;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.json.JacksonJsonObjectMarshaller;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonFileItemWriter;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Configuration
public class SampleJob {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private FirstItemReader firstItemReader;

    @Autowired
    private FirstItemProcessor firstItemProcessor;

    @Autowired
    private FirstItemWriter firstItemWriter;

    @Autowired
    private SkipListener skipListener;

    @Autowired
    private SkipListenerImpl skipListenerImpl;

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource datasource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.universitydatasource")
    public DataSource universitydatasource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public Job chunkJob() {
        return jobBuilderFactory.get("Chunk Job")
                .incrementer(new RunIdIncrementer())
                .start(firstChunkStep())
                .build();
    }

    @Bean
    public Step firstChunkStep() {
        return stepBuilderFactory.get("First Chunk Step")
                .<StudentCsv, StudentJson>chunk(3)
                .reader(flatFileItemReader(null))
                .processor(firstItemProcessor)
                .writer(jsonFileItemWriter(null))
                .faultTolerant()
//                .skip(FlatFileParseException.class)
//                .skip(NullPointerException.class)
                .skip(Throwable.class)
                .skipLimit(10)
//                .skipPolicy(new AlwaysSkipItemSkipPolicy())
                .retryLimit(3)
                .retry(Throwable.class)
//                .listener(skipListener)
                .listener(skipListenerImpl)
                .build();
    }

    @Bean
    @StepScope
    public FlatFileItemReader<StudentCsv> flatFileItemReader(
            @Value("#{jobParameters['inputFile']}") FileSystemResource fileSystemResource) {

        FlatFileItemReader<StudentCsv> flatFileItemReader = new FlatFileItemReader<>();

        flatFileItemReader.setResource(fileSystemResource);

        flatFileItemReader.setLineMapper(new DefaultLineMapper<StudentCsv>() {
            {
                setLineTokenizer(new DelimitedLineTokenizer() {
                    {
                        setNames("ID", "First Name", "Last Name", "Email");
                        setDelimiter("|");
                    }
                });

                setFieldSetMapper(new BeanWrapperFieldSetMapper<StudentCsv>() {
                    {
                        setTargetType(StudentCsv.class);
                    }
                });
            }
        });

        flatFileItemReader.setLinesToSkip(1);

        return flatFileItemReader;
    }

    @Bean
    @StepScope
    public JsonItemReader<StudentJson> jsonItemReader(
            @Value("#{jobParameters['inputFile']}") FileSystemResource fileSystemResource) {

        JsonItemReader<StudentJson> jsonItemReader = new JsonItemReader<>();

        jsonItemReader.setResource(fileSystemResource);
        jsonItemReader.setJsonObjectReader(new JacksonJsonObjectReader<>(StudentJson.class));
        jsonItemReader.setMaxItemCount(8);
        jsonItemReader.setCurrentItemCount(2);

        return jsonItemReader;
    }

    @Bean
    @StepScope
    public StaxEventItemReader<StudentXml> staxEventItemReader(
            @Value("#{jobParameters['inputFile']}") FileSystemResource fileSystemResource) {

        StaxEventItemReader<StudentXml> staxEventItemReader = new StaxEventItemReader<>();

        staxEventItemReader.setResource(fileSystemResource);
        staxEventItemReader.setFragmentRootElementName("student");
        staxEventItemReader.setUnmarshaller(new Jaxb2Marshaller() {
            {
                setClassesToBeBound(StudentXml.class);
            }
        });

        return staxEventItemReader;
    }

    public JdbcCursorItemReader<StudentJdbc> jdbcCursorItemReader(
            @Value("#{jobParameters['inputFile']}") FileSystemResource fileSystemResource) {

        JdbcCursorItemReader<StudentJdbc> jdbcCursorItemReader = new JdbcCursorItemReader<>();

        jdbcCursorItemReader.setDataSource(universitydatasource());

        jdbcCursorItemReader.setSql("SELECT id, first_name as firstName, last_name as lastName, email\n" +
                "FROM university.students\n");

        jdbcCursorItemReader.setRowMapper(new BeanPropertyRowMapper<StudentJdbc>() {
            {
                setMappedClass(StudentJdbc.class);
            }
        });

        jdbcCursorItemReader.setCurrentItemCount(2);

        jdbcCursorItemReader.setMaxItemCount(8);

        return jdbcCursorItemReader;
    }

    @Bean
    @StepScope
    public FlatFileItemWriter<StudentJdbc> flatFileItemWriter(
            @Value("#{jobParameters['outputFile']}") FileSystemResource fileSystemResource) {

        FlatFileItemWriter<StudentJdbc> flatFileItemWriter = new FlatFileItemWriter<>();

        flatFileItemWriter.setResource(fileSystemResource);

        flatFileItemWriter.setHeaderCallback(new FlatFileHeaderCallback() {
            @Override
            public void writeHeader(Writer writer) throws IOException {
                writer.write("Id,First Name,Last Name,Email");
            }
        });

        flatFileItemWriter.setLineAggregator(new DelimitedLineAggregator<StudentJdbc>() {
            {
//                setDelimiter("|");
                setFieldExtractor(new BeanWrapperFieldExtractor<StudentJdbc>() {
                    {
                        setNames(new String[] { "id", "firstName", "lastName", "email" });
                    }
                });
            }
        });

        flatFileItemWriter.setFooterCallback(new FlatFileFooterCallback() {
            @Override
            public void writeFooter(Writer writer) throws IOException {
                writer.write("Created @ " + new Date());
            }
        });

        return flatFileItemWriter;
    }

    @Bean
    @StepScope
    public JsonFileItemWriter<StudentJson> jsonFileItemWriter(
            @Value("#{jobParameters['outputFile']}") FileSystemResource fileSystemResource) {

        JsonFileItemWriter<StudentJson> jsonFileItemWriter = new JsonFileItemWriter<StudentJson>(fileSystemResource,
                new JacksonJsonObjectMarshaller<StudentJson>()) {

            @Override
            public String doWrite(List<? extends StudentJson> items) {
                items.forEach(item -> {
                    if (item.getId() == 3) {
                        System.out.println("Inside jsonFileItemWritor, id = 3");
                        throw new NullPointerException();
                    }
                });
                return super.doWrite(items);
            }
        };

        return jsonFileItemWriter;
    }

    @Bean
    @StepScope
    public StaxEventItemWriter<StudentJdbc> staxEventItemWriter(
            @Value("#{jobParameters['outputFile']}") FileSystemResource fileSystemResource) {

        StaxEventItemWriter<StudentJdbc> staxEventItemWriter = new StaxEventItemWriter<>();

        staxEventItemWriter.setResource(fileSystemResource);
        staxEventItemWriter.setRootTagName("students");

        staxEventItemWriter.setMarshaller(new Jaxb2Marshaller(){
            {
                setClassesToBeBound(StudentJdbc.class);
            }
        });

        return staxEventItemWriter;
    }

    @Bean
    public JdbcBatchItemWriter<StudentCsv> jdbcBatchItemWriter() {

        JdbcBatchItemWriter<StudentCsv> jdbcBatchItemWriter = new JdbcBatchItemWriter<>();

        jdbcBatchItemWriter.setDataSource(universitydatasource());
        jdbcBatchItemWriter.setSql("INSERT INTO students\n" +
                "(id, first_name, last_name, email)\n" +
                "VALUES(:id, :firstName, :lastName, :email);\n");

        jdbcBatchItemWriter.setItemSqlParameterSourceProvider(
                new BeanPropertyItemSqlParameterSourceProvider<StudentCsv>());

        return jdbcBatchItemWriter;
    }

    @Bean
    public JdbcBatchItemWriter<StudentCsv> jdbcBatchItemWriter1() {

        JdbcBatchItemWriter<StudentCsv> jdbcBatchItemWriter = new JdbcBatchItemWriter<>();

        jdbcBatchItemWriter.setDataSource(universitydatasource());
        jdbcBatchItemWriter.setSql("INSERT INTO students\n" +
                "(id, first_name, last_name, email)\n" +
                "VALUES (?,?,?,?)\n");

        jdbcBatchItemWriter.setItemPreparedStatementSetter(
                new ItemPreparedStatementSetter<StudentCsv>() {
                    @Override
                    public void setValues(StudentCsv studentCsv, PreparedStatement preparedStatement) throws SQLException {
                        preparedStatement.setLong(1, studentCsv.getId());
                        preparedStatement.setString(2, studentCsv.getFirstName());
                        preparedStatement.setString(3, studentCsv.getLastName());
                        preparedStatement.setString(4, studentCsv.getEmail());
                    }
                }
        );

        return jdbcBatchItemWriter;
    }
}
