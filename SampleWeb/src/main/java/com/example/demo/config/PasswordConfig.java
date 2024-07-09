package com.example.demo.config;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordConfig {

	// 처음에 에러가 뜨는 이유는 라이브러리가 없기 때문
	// pom.xml에 dependency를 추가해야한다.

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public Mapper mapper() {
		return new DozerBeanMapper();
	}
}
