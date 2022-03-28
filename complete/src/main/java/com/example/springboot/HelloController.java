package com.example.springboot;

import com.github.javafaker.Faker;
import com.google.gson.Gson;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class HelloController {

	@GetMapping("/")
	public String index() {
		return "GS Spring Boot (Azure Quickstart 2) Index page";
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(required = false) String name) {
		if (name == null) {
			return "Hello from Azure Quickstart 2";
		} else {
			return "Hello from Azure Quickstart 2 "+name;
		}
	}

	@GetMapping("/greetings/{name}")
	public String greetings(@PathVariable("name") String name) {
		return "Greetings from Azure Quickstart 2 "+name;
	}

	@GetMapping("/oops")
	public String oops() {
		throw new RuntimeException("Oops, I messed up");
	}

	@GetMapping("/person")
	public Person person(@RequestParam(required = false) String name) {
		Faker faker = new Faker();
		int age = (int) (Math.random() * (100 - 1)) + 1;
		Person p = new Person(faker.name().fullName(), age);
		log.info("Returning new person : {}", new Gson().toJson(p));
		return p;
	}

	@Data
	static class Person {
		
		private final String name;
		private final int age;
	}

}
