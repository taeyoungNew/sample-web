package com.example.demo.constant;

import org.hibernate.engine.internal.StatisticalLoggingSessionEventListener;

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
	
	// 찾는 유저가 데이터베이스에 없을 경우
	public static final String USERIST_DELETE_SUCCEED = "userList.deleteSucceed";
	
	// 유저삭제에 성공했다는 메세지
	public static final String USERLIST_NON_EXISTED_USER_ID = "userList.nonExistedUserId";
	
	public static final String USER_UPDATE_NON_EXISTED_USER_ID = "userUpdate.nonExistedUserId";
	
	// 유저정보 갱신에 성공한 메세지
	public static final String USER_UPDATE_SUCCEED = "userUpdate.succeed";
	
	// 유저정보 갱신에 실패한 메세지
	public static final String USER_UPDATE_FAILED = "userUpdate.failed";
	
	
}
