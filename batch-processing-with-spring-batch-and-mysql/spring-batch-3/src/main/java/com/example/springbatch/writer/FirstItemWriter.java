package com.example.springbatch.writer;

import com.example.springbatch.model.StudentCsv;
import com.example.springbatch.model.StudentJdbc;
import com.example.springbatch.model.StudentJson;
import com.example.springbatch.model.StudentXml;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FirstItemWriter implements ItemWriter<StudentJdbc> {

    @Override
    public void write(List<? extends StudentJdbc> list) throws Exception {
        System.out.println("Inside Item writer");
        list.forEach(System.out::println);
    }
}
