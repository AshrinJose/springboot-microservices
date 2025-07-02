package com.learning.employeeservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Employee microservice API documentation",
				description = "Employee microservice API documentation",
				version = "v1.0"
		)
)
@EnableFeignClients // enables component scanning for interfaces declared as Feign Clients
public class EmployeeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeServiceApplication.class, args);
	}

}
