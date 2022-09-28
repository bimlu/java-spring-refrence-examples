package com.example.springbatch.listener;

import com.example.springbatch.model.StudentCsv;
import com.example.springbatch.model.StudentJson;
import org.springframework.batch.core.annotation.OnSkipInProcess;
import org.springframework.batch.core.annotation.OnSkipInRead;
import org.springframework.batch.core.annotation.OnSkipInWrite;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.util.Date;

@Component
public class SkipListener {

    @OnSkipInRead
    public void skipInRead(Throwable th) {

        if (th instanceof FlatFileParseException) {
            createFile("/home/robin/dev/cynoteck/java-training-guide/batch-processing-with-spring-batch" +
                            "-and-mysql/spring-batch/chunk-job/first-chunk-step/reader/skipInRead.txt",
                    ((FlatFileParseException) th).getInput());
        }
    }

    @OnSkipInProcess
    public void skipInProcess(StudentCsv studentCsv, Throwable th) {

        createFile("/home/robin/dev/cynoteck/java-training-guide/batch-processing-with-spring-batch" +
                        "-and-mysql/spring-batch/chunk-job/first-chunk-step/processor/skipInProcess.txt",
                studentCsv.toString());
    }

    @OnSkipInWrite
    public void skipInWrite(StudentJson studentJson, Throwable th) {

        createFile("/home/robin/dev/cynoteck/java-training-guide/batch-processing-with-spring-batch" +
                        "-and-mysql/spring-batch/chunk-job/first-chunk-step/writer/skipInWrite.txt",
                studentJson.toString());
    }
    public void createFile(String filePath, String data) {

        try (FileWriter fileWriter = new FileWriter(new File(filePath), true)) {
            fileWriter.write(data + new Date() + "\n");
        } catch (Exception e) {

        }
    }
}
