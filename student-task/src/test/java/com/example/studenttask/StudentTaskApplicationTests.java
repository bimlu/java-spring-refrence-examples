package com.example.studenttask;

import com.example.studenttask.entities.Student;
import com.example.studenttask.entities.StudentName;
import com.example.studenttask.repos.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StudentTaskApplicationTests {

	@Autowired
	private StudentRepository studentRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void testCreateStudent() {

		Student student = new Student();
		student.setEmail("akash@test.com");
		student.setMobileNumber("1234567890");
		student.setRollNumber("123456");
		student.setStudentName(new StudentName("Akash", "Singh", "Bisht"));
		studentRepository.save(student);
	}

	@Test
	void testNullCreateStudent() {

		Student student = new Student();
		student.setEmail("akash@test.com");
		student.setMobileNumber("1234567890");
		student.setRollNumber("123456");
		student.setStudentName(new StudentName("Akash", null, "Bisht"));
		studentRepository.save(student);
	}

	@Test
	void testFindStudent() {

		System.out.println(studentRepository.findById(1L).get());
	}

}
