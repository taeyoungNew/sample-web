package com.example.demo.form;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class SignupReqForm {
	@Length(min = 8, max = 20)
	private String userId;
	@Length(min = 5, max = 20)
	private String password;
}
