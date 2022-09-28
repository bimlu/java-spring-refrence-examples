package com.example.springbatch.processor;

import com.example.springbatch.model.StudentCsv;
import com.example.springbatch.model.StudentJdbc;
import com.example.springbatch.model.StudentJson;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class FirstItemProcessor implements ItemProcessor<StudentCsv, StudentJson> {

    @Override
    public StudentJson process(StudentCsv item) throws Exception {
        System.out.println("Inside Item Processor");

        if (item.getId() == 6) {
            System.out.println("Inside FirstItemProcessor, id = 6");
            throw new NullPointerException();
        }

        StudentJson studentJson = new StudentJson();
        studentJson.setId(item.getId());
        studentJson.setFirstName(item.getFirstName());
        studentJson.setEmail(item.getEmail());

        return studentJson;
    }
}
