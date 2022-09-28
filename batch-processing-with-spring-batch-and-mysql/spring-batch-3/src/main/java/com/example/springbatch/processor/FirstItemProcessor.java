package com.example.springbatch.processor;

import com.example.springbatch.model.StudentCsv;
import com.example.springbatch.model.StudentJdbc;
import com.example.springbatch.model.StudentJson;
import com.example.springbatch.postgres.entities.Student;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class FirstItemProcessor implements ItemProcessor<Student, com.example.springbatch.mysql.entities.Student> {

    @Override
    public com.example.springbatch.mysql.entities.Student process(Student item) throws Exception {

        System.out.println("Processing " + item.getId());

        com.example.springbatch.mysql.entities.Student student = new com.example.springbatch.mysql.entities.Student();

        student.setId(item.getId());
        student.setFirstName(item.getFirstName());
        student.setLastName(item.getLastName());
        student.setEmail(item.getEmail());
        student.setDeptId(item.getDeptId());
        student.setIsActive(item.getIsActive() != null && Boolean.parseBoolean(item.getIsActive()));

        return student;
    }
}
