package com.example.springbatch.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FirstItemWriter implements ItemWriter<Long> {

    @Override
    public void write(List<? extends Long> list) throws Exception {
        System.out.println("Inside Item writer");
        list.forEach(System.out::println);
    }
}
