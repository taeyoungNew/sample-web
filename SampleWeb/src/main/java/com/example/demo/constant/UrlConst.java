package com.example.demo.constant;

/**
 * URL클래스
 */
public class UrlConst {
	
	// 로그인 페이지
	public static final String LOGIN = "/login";
	// 회원가입 페이지
	public static final String SIGNUP = "/signup";
	// 메뉴 페이지
	public static final String MENU = "/menu";
	// 인증이 필요없는 페이지
	public static final String[] NO_AUTHENTICATION = {LOGIN, SIGNUP, "/webjars/**"};
	
}
