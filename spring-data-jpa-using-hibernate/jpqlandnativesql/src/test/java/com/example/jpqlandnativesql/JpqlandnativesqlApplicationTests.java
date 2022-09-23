package com.example.jpqlandnativesql;

import com.example.jpqlandnativesql.entities.Student;
import com.example.jpqlandnativesql.repos.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class JpqlandnativesqlApplicationTests {

	@Autowired
	private StudentRepository repository;

	@Test
	public void testStudentCreate() {

		Student student = new Student();
		student.setFirstName("Robin");
		student.setLastName("Rawat");
		student.setScore(69);

		repository.save(student);

		Student student2 = new Student();
		student2.setFirstName("Akash");
		student2.setLastName("Bisht");
		student2.setScore(82);

		repository.save(student2);
	}

	@Test
	public void testFindAllStudents() {
		repository.findAllStudents(PageRequest.of(1, 5, Sort.Direction.DESC, "firstName", "id")).forEach(System.out::println);
	}

	@Test
	public void testFindAllStudentsPartialData() {
		repository.findAllStudentsPartialData().forEach(objects -> System.out.println(objects[0] + ", " + objects[1]));
	}

	@Test
	public void testFindAllStudentsByFirstName() {
		repository.findAllStudentByFirstName("Robin").forEach(System.out::println);
	}

	@Test
	public void testFindAllStudentsForGivenScores() {
		repository.findStudentsForGivenScores(60, 80).forEach(System.out::println);
	}

	@Test
	@Transactional
	@Rollback(value = false)
	public void testDeleteStudentsByFirstName() {
		repository.deleteStudentsByFirstName("Robin");
	}

	@Test
	public void testFindAllStudentNQ() {
		repository.findAllStudentsNQ().forEach(System.out::println);
	}

	@Test
	public void testFindStudentByFirstNameNQ() {
		repository.findStudentByFirstNameNQ("Robin").forEach(System.out::println);
	}
}
