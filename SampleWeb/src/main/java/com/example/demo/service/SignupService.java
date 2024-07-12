package com.example.demo.service;

import java.util.Optional;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.UserInfoEntity;
import com.example.demo.constant.AuthorityKind;
import com.example.demo.dto.SignupReqDto;
import com.example.demo.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SignupService {

	private final UserInfoRepository repository;
	// Dozer Mapper
	private final Mapper mapper;
	
	private final PasswordEncoder passwordEncoder;
	
	/**
	 * 화면에 입력한 정보를 유저테이블에 신규저장합니다. 
	 * 
	 * <p> 이하의 테이블컬럼들 유저테이블의 전체 항목이 아닙니다.
	 * <ul>
	 * <li> 패스워드 : 화면에 입력한 패스워드는 해쉬화되어 저장됩니다. </li>
	 * <li> 권한 : 기본적으로 '상품정보확인이 가능'한 값이 저장됩니다. </li>
	 * </ul> 
	 * 
	 * @param signupForm
	 * @return 가입정보(유저정보Entity)
	 */
	public Optional<UserInfoEntity> resistUserInfo(SignupReqDto form) {
		// Optional : java8부터 도입된 Optional은 값이 없는 경우를 표현하기 위한 클래스이다.
		// <>(제네릭)을 사용하여 어떤 타입의 객체도 감싸서 담을 수 있다. 
		// 값이 존재할 수도 존재하지않을 수도 있으며 
		// NullPointException 예외를 방지하고 코드의 안정성을 높이며 가독성을 향상시킨다.
		// Id확인하기
		Optional<UserInfoEntity> userInfoExisted = repository.findById(form.getUserId());
		if(userInfoExisted.isPresent()) {
			return Optional.empty();
		}
		
		/**
		 * 회원가입 메서드
		 */
		UserInfoEntity userInfo = mapper.map(form, UserInfoEntity.class);
		String encoderPassword = passwordEncoder.encode(form.getPassword());
		
		userInfo.setPassword(encoderPassword);
		userInfo.setAuthority(AuthorityKind.ITEM_WATCHER.getAuthorityKind());
		
		System.out.println("userInfo.toString() = " + userInfo.toString());
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
