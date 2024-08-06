package com.example.demo.form;

import com.example.demo.constant.db.AuthorityKind;
import com.example.demo.constant.db.UserStatusKind;

import lombok.Getter;
import lombok.Setter;

/**
 * 유저갱신화면의 dto클래스
 * 
 */
@Setter
@Getter
public class UserUpdateForm {
	
	// 로그인 실패횟수와 유저락날짜를 리셋할지의 여부
	private boolean resetsLoginFailure;
	
	// 어카운트상태 종류
	private UserStatusKind userStatusKind;
	
	// 유저권한종류
	private AuthorityKind authorityKind;
	
}
