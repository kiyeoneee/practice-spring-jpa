package com.kiyeon.practicespringjpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;
import java.util.UUID;

// update는 null로 처리하고 싶다면 아래의 옵션을 통해 사용은 가능, 일반적이진 않음
// @EnableJpaAuditing(modifyOnCreate = false)
@EnableJpaAuditing
@SpringBootApplication
// SpringBoot 를 사용하면 자동으로 찾아옴
//@EnableJpaRepositories(basePackages = "com.kiyeon.practicespringjpa.chapter3.repository")
public class PracticeSpringJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PracticeSpringJpaApplication.class, args);
	}

	@Bean
	public AuditorAware<String> auditorProvider() {
		return () -> Optional.of(UUID.randomUUID().toString());
	}
}
