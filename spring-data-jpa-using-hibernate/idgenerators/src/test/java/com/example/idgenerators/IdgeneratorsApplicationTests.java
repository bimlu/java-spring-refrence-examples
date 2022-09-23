package com.example.idgenerators;

import com.example.idgenerators.entities.Employee;
import com.example.idgenerators.repos.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class IdgeneratorsApplicationTests {

	@Autowired
	EmployeeRepository er;

	@Test
	void testCreateEmployee() {

		Employee employee = new Employee();
		employee.setId(123L);
		employee.setName("John");

		er.save(employee);
	}

}
