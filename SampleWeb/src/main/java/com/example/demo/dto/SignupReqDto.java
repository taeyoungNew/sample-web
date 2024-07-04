package com.example.demo.dto;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class SignupReqDto {
	@Length(min = 8, max = 20)
	private String userId;
	@Length(min = 5, max = 20)
	private String password;
}
