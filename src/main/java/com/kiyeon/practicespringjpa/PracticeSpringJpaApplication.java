package com.kiyeon.practicespringjpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
// SpringBoot 를 사용하면 자동으로 찾아옴
//@EnableJpaRepositories(basePackages = "com.kiyeon.practicespringjpa.repository")
public class PracticeSpringJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PracticeSpringJpaApplication.class, args);
	}

}
