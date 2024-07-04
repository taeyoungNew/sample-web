package com.example.demo.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LoginMessage {
	INVALID_PASSWORD(MessageConst.LOGIN_INVALID_PASSWORD, false),
	INCORRECT_USER_ID(MessageConst.LOGIN_INCORRECT_USER_ID, false);
	
	private String message;
	private boolean isError;
}
