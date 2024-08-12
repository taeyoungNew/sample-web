package com.example.demo.form;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class SignupReqForm {
	// 유저ID
	@Length(min = 8, max = 20)
	private String userId;
	
	// 패스워드
	@Length(min = 5, max = 20)
	private String password;
	
	// 이메일주소
	// message={message.properties의 키}를 설정하면 체크에러가 발생하였을 때 message.properties의 메세지가
	// 호출된다.
	@Length(max = 100)
	@Pattern(regexp = "^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$", message = "{signup.invalidMailAddress}")
	private String mailAddress;

}
