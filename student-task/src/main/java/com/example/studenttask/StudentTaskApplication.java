package com.example.studenttask;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Student API",
				version = "1.0",
				description = "A simple demo Student API",
				contact = @Contact(
						name = "Robin Rawat",
						url = "github.com/bimlu",
						email = "robin.rawat@cynoteck.com"
				),
				license = @License(
						name = "MIT",
						url = "https://www.mit.edu/~amini/LICENSE.md"
				),
				termsOfService = "example.com/terms-of-service.html"
		)
)
public class StudentTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentTaskApplication.class, args);
	}

}
