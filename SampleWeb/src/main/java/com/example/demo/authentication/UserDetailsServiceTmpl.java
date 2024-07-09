package com.example.demo.authentication;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.demo.Entity.UserInfoEntity;
import com.example.demo.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;

/**
 * 유저정보 생성
 */
@Component
@RequiredArgsConstructor
public class UserDetailsServiceTmpl implements UserDetailsService {
	
	private final UserInfoRepository userInfoRepository;
	
	// String username = 로그인아이디를 받는다. 
	// 디포트설정으로는 name = username의 input값을 받지만 
	// 해당 클래스를 구현함으로써 username = userId의 값이 들어오게 되는 것이다.
	/**
	 * @param username userId
	 * @throws UsernameNotFoundException
	 */
	@Override	// String name = 로그인아이디를 받음
	public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {
		
		
		UserInfoEntity userInfo = userInfoRepository.findById(username)
				// 유저의 ID를 찾지못했을 때의 에러처리 : UsernameNotFoundException
				.orElseThrow(() -> new UsernameNotFoundException(username));
		
		// User클래스 : Spring Security에서 제동하는 디폰트 사용자모델로
		// UserDetails인터페이스를 구현하고 있어 사용자의 인증정보와 권한정보를 제공한다.
		return User.withUsername(userInfo.getUserId())	// User
					.password(userInfo.getPassword())
					.roles("USER")
//					.disabled(userInfo.isDisabled())
//					.accountLocked(isAccountLocked)
					.build();
	}
}
