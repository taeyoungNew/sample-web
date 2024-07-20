package com.example.demo.service;

import java.util.Optional;

import com.example.demo.dto.SignupReqDto;
import com.example.demo.entity.UserInfoEntity;

/**
 * 회원가입Service인터페이스
 * 
 */

public interface SignupService {
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
	public Optional<UserInfoEntity> resistUserInfo(SignupReqDto form);
}
