package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.constant.AuthorityKind;
import com.example.demo.constant.UserStatusKind;
import com.example.demo.dto.SignupReqDto;
import com.example.demo.entity.UserInfoEntity;
import com.example.demo.repository.UserInfoRepository;
import com.github.dozermapper.core.Mapper;

import lombok.RequiredArgsConstructor;

/**
 * 회원가입서비스 구현클래스 DI란 'dependency injection'의 약자로 의존성주입이라고 불린다.
 * 
 * 
 */

@Service
@RequiredArgsConstructor
public class SignupServiceImpl implements SignupService {

	// 유저테이블 Repository클래스
	private final UserInfoRepository repository;
	
	// Dozer Mapper
	private final Mapper mapper;
	
	// 패스워드 엔코더
	private final PasswordEncoder passwordEncoder;
	
	/**
	 * {@inheritDoc}
	 */
	
	@Override	
	public Optional<UserInfoEntity> resistUserInfo(SignupReqDto form) {
		// Optional : java8부터 도입된 Optional은 값이 없는 경우를 표현하기 위한 클래스이다.
		// <>(제네릭)을 사용하여 어떤 타입의 객체도 감싸서 담을 수 있다.
		// 값이 존재할 수도 존재하지않을 수도 있으며
		// NullPointException 예외를 방지하고 코드의 안정성을 높이며 가독성을 향상시킨다.
		// Id확인하기
		Optional<UserInfoEntity> userInfoExisted = repository.findById(form.getUserId());
		if (userInfoExisted.isPresent()) {
			return Optional.empty();
		}

		/**
		 * 회원가입 메서드
		 */
		UserInfoEntity userInfo = mapper.map(form, UserInfoEntity.class);
		String encoderPassword = passwordEncoder.encode(form.getPassword());

		userInfo.setPassword(encoderPassword);
		// 아큐먼트로 넘길 ENUM의 필드값자체를 넘겨줌
		userInfo.setStatus(UserStatusKind.ENBLED);	// user_info의 is_disabled를 0으로 저장
		userInfo.setAuthority(AuthorityKind.ITEM_WATCHER);	// user_info의 authority를 1로 저장
		userInfo.setCreateTime(LocalDateTime.now());
		userInfo.setUpdateTime(LocalDateTime.now());
		userInfo.setUpdateUser(form.getUserId());
		return Optional.of(repository.save(userInfo));
	}

//	public boolean checkId(SignupReqDto form) {
//		
//		Optional<UserInfoEntity> userId = repository.findById(form.getUserId());
//		
//		if(userId.isPresent()) {
//			return true;
//		} 
//		
//		return false;
//	}
//	
}
