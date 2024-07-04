package com.example.demo.constant;

/**
 * 에러메세지ID상수클래스
 * 
 */

public class MessageConst {
	// 로그인화면 : 로그인에 실패했을 경우
	public static final String LOGIN_WRONG_INPUT = "login.wrongInput";
	
	// 회원가입화면 : 이미 아이디가 존재하는 경우 
	public static final String SIGNUP_EXISTED_USER_ID = "signup.existedUserId";
	
	// 회원가입화면 : 회원가입에 성공하였을 경우
	public static final String SIGNUP_RESIST_SUCCESS = "signup.resistSuccess";

	public static final String LOGIN_INVALID_PASSWORD = "login.invalidPassword";

	public static final String LOGIN_INCORRECT_USER_ID = "login.incorrectUserId";

	// 입력오류
	public static final String FORM_ERROR = "common.formError";
}
