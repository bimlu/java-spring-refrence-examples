package com.example.associations;

import com.example.associations.onetomany.entities.Customer;
import com.example.associations.onetomany.entities.PhoneNumber;
import com.example.associations.manytomany.entities.Programmer;
import com.example.associations.manytomany.entities.Project;
import com.example.associations.onetomany.repos.CustomerRepository;
import com.example.associations.manytomany.repos.ProgrammereRepository;
import com.example.associations.onetoone.entities.License;
import com.example.associations.onetoone.entities.Person;
import com.example.associations.onetoone.repos.LicenseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;

@SpringBootTest
class JpaAssociationsApplicationTests {

	@Autowired
	CustomerRepository repository;

	@Autowired
	ProgrammereRepository programmereRepository;

	@Autowired
	LicenseRepository licenseRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void testCreateCustomer() {

		Customer customer = new Customer();
		customer.setName("John");

		PhoneNumber ph1 = new PhoneNumber();
		ph1.setNumber("1234567890");
		ph1.setType("mobile");

		PhoneNumber ph2 = new PhoneNumber();
		ph2.setNumber("987654");
		ph2.setType("cell");

		customer.addPhoneNumber(ph1);
		customer.addPhoneNumber(ph2);

		repository.save(customer);
	}

	@Test
	@Transactional
	public void testLoadCustomer() {
		Customer customer = repository.findById(4L).get();
		System.out.println(customer.getName());

		customer.getNumbers().forEach(p -> System.out.println(p.getNumber()));
	}

	@Test
	public void testUpdateCustomer() {
		Customer customer = repository.findById(4L).get();
		customer.setName("John Bush");

		customer.getNumbers().forEach(number -> number.setType("cell"));

		repository.save(customer);
	}

	@Test
	public void testDelete() {
		repository.deleteById(4L);
	}

	@Test
	public void testmtomCreateProgrammer() {
		Programmer programmer = new Programmer();
		programmer.setName("John");
		programmer.setSal(10000);

		HashSet<Project> projects = new HashSet<>();

		Project project = new Project();
		project.setName("Hibernate Project");

		projects.add(project);

		programmer.setProjects(projects);

		programmereRepository.save(programmer);
	}

	@Test
	@Transactional
	public void testmtmFindProgrammer() {
		Programmer programmer = programmereRepository.findById(1).get();
		System.out.println(programmer);

		System.out.println(programmer.getProjects());
	}

	@Test
	public void testotoCreateLicense() {
		License license = new License();
		license.setType("CAR");
		license.setValidFrom(new Date());
		license.setValidTo(new Date());

		Person person = new Person();
		person.setFirstName("John");
		person.setLastName("Clinton");
		person.setAge(35);

		license.setPerson(person);

		licenseRepository.save(license);
	}
}
