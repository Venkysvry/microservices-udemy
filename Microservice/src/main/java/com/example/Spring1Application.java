package com.example;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(
		info=@Info(
				title = "Spring booot Rest Api Documentation",
				description = "Spring booot Rest Api Documentation",
				version = "v1.0",
				contact= @Contact(
						name="venkatesh",
						email="venkateshkurva46@gmail.com"
						
						)
				)
		)
public class Spring1Application {


	public static void main(String[] args) {
		SpringApplication.run(Spring1Application.class, args);
	}
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
