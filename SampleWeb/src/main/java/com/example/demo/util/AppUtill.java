package com.example.demo.util;

import java.util.Locale;

import org.springframework.context.MessageSource;

/**
 * 어플리케이션 공통클래스
 * 
 */
public class AppUtill {

	/**
	 * 메세지ID로부터 메세지를 가지고 온다
	 * @param messageSource 메세지소스
	 * @param key 메세지키
	 * @param params 어떤문자의 소스를 가지고 올건지
	 * @return 메세지
	 * 
	 */

	// 스프링부트에서 messsage를 얻기위해서는 MessageSource인터페이스를 사용한다.
	public static String getMessage(MessageSource messageSource, String key, Object... params) {
		// getMessage의 첫번째 인자는 key,
		// 두번째 인자는 가변변수타입으로 받는데 외부에서 들어오는 데이터를 params으로 받는다.
		return messageSource.getMessage(key, params, Locale.KOREA);
	}
}
