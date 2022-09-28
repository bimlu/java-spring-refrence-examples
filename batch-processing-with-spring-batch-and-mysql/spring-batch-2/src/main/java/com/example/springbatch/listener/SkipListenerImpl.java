package com.example.springbatch.listener;

import com.example.springbatch.model.StudentCsv;
import com.example.springbatch.model.StudentJson;
import org.springframework.batch.core.SkipListener;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.util.Date;

@Component
public class SkipListenerImpl implements SkipListener<StudentCsv, StudentJson> {

    @Override
    public void onSkipInRead(Throwable t) {
        if (t instanceof FlatFileParseException) {
            createFile("/home/robin/dev/cynoteck/java-training-guide/batch-processing-with-spring-batch" +
                            "-and-mysql/spring-batch/chunk-job/first-chunk-step/reader/skipInRead.txt",
                    ((FlatFileParseException) t).getInput());
        }
    }

    @Override
    public void onSkipInWrite(StudentJson item, Throwable t) {
        createFile("/home/robin/dev/cynoteck/java-training-guide/batch-processing-with-spring-batch" +
                        "-and-mysql/spring-batch/chunk-job/first-chunk-step/writer/skipInWrite.txt",
                item.toString());
    }

    @Override
    public void onSkipInProcess(StudentCsv item, Throwable t) {
        createFile("/home/robin/dev/cynoteck/java-training-guide/batch-processing-with-spring-batch" +
                        "-and-mysql/spring-batch/chunk-job/first-chunk-step/processor/skipInProcess.txt",
                item.toString());
    }

    public void createFile(String filePath, String data) {

        try (FileWriter fileWriter = new FileWriter(new File(filePath), true)) {
            fileWriter.write(data + new Date() + "\n");
        } catch (Exception e) {

        }
    }
}
