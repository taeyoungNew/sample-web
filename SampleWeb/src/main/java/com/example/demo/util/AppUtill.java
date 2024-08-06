package com.example.demo.util;

import java.util.Locale;

import org.springframework.context.MessageSource;

import com.example.demo.entity.UserInfoEntity;

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
	
	/**
	 * DB의 LIKE검색용으로 매개변수의 앞뒤에 '%'로 감싸서 리턴한다.
	 * 
	 * @param UserInfoEntity userInfo
	 * @return 전후에 와일드카드를 붙인 파라미터 
	 */
	public static String addWildcard(String param) {
		return "%" + param + "%";
	}
	
	/**
	 * 리다이렉트 장소에 URL을 전달하고 리다이렉트 할 URL을 작성한다.
	 * 
	 * @param url 리다이렉트 URL
	 * @return 리다이렉트의 URL
	 */
	public static String doRedirect(String url) {
		return "redirect:" + url;
	}
	
	
}
