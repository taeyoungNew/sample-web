package com.example.demo.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.constant.UrlConst;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {
	
	// 패스워드 엔코더
	private final PasswordEncoder passwordEncoder;
	
	// 유저정보 취득 Service 
	private final UserDetailsService userDetailsService;
	
	// 메세지정보 
	private final MessageSource messageSource;

	// 유저명의 name속성
	private final String USERNAME_PARAMETER = "userId";

    /**
     * 
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		/**
		 * @param http 커스터마이즈 파라미터
		 * @return 커스터마이즈결과
		 * @throws Exception 예외처리
		 */

//		http.authorizeHttpRequests(authorize -> 
//			authorize.requestMatchers(UrlConst.NO_AUTHENTICATION).permitAll() // 인증이 필요없는 page URL
//				.anyRequest().authenticated());	// 그 나머지(anyRequests)는 인증이 필요하다는 설정(authenticated)
		
		// /login 페이지를 호출
		// http.formLogin을 직접정의하지않으면 spring security의 defult login페이지가 호출된다.
		
		// !!! 기본적으로 WebSecurityConfig에서 ID값을 username이라는 이름으로 받게된다.
		// 따라서 자신이 ID의 이름을 username이 아닌 다른 이름으로 설정을 했을 때 securityFilterChain
		// 에서 ID값을 찾지 못한다.
		
		// 그래서 나는 usernameParameter로 ID값을 username이 아닌 내가 설정해놓은 userId로 받겠다고 정의한다.
		http.authorizeHttpRequests(authorize -> 
				authorize.requestMatchers(UrlConst.NO_AUTHENTICATION).permitAll() // 인증이 필요없는 page URL
					.anyRequest().authenticated())
			.formLogin(login -> 
				login.loginPage(UrlConst.LOGIN)	// 커스텀 로그인 화면을 사용하기위한 설정
					 .usernameParameter(USERNAME_PARAMETER)	// 유저명 파라미터의 name속성
					 .defaultSuccessUrl(UrlConst.MENU)// 로그인 성공시 이동하는 page URL
			)
			// logout이 계속 오류가 났던 이유 : header.html의 th:action="@{/logout}"의 'th'가 없어서 
			.logout(authorize -> authorize
//					.logoutUrl("/logout")	// 로그아웃 구현
					.logoutSuccessUrl(UrlConst.SIGNUP)
			);	

		return http.build();
	}
	
    /**
     * Provider 정의
     * 
     * @return 커스텀 Provider정보
     */
    
	@Bean	// AuthenticationProvider를 @Bean하므로써 기존의 Provider에서 커스텀 Provider로 쓸 수 있다.
	AuthenticationProvider daoAuthenticationProvider() {
		// DaoAuthenticationProvider : UserDetailsService와 PasswordEncoder로 username/password를 인증하는
		// AuthenticationProvider 구현체이다.
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		// @Bean정의한 DaoAuthenticationProvider객체를 사용하기 위해서는 SetUserDetatilsService와
		// setPasswordEncoder는 반드시 세팅해줘야한다.
		provider.setUserDetailsService(userDetailsService);	
		provider.setPasswordEncoder(passwordEncoder);
		// 자신의 커스텀 메세지내용을 반환하고싶으면 setMessage를 해서 
		// provider가 message.properties를 참조하게 해야한다.
		provider.setMessageSource(messageSource);
		
		return provider;
	}
}
