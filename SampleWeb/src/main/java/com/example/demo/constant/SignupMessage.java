package com.example.demo.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor	// 모든 필드값을 파라미터로 받는 생성자를 생성
public enum SignupMessage {
	SUCCESS(MessageConst.SIGNUP_RESIST_SUCCESS, false),
	EXISTED_LOGIN_ID(MessageConst.SIGNUP_EXISTED_USER_ID, true);
	
	private String message;
	
	private boolean isError;
}
