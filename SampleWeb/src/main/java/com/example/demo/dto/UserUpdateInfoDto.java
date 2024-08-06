package com.example.demo.dto;

import java.time.LocalDateTime;

import com.example.demo.constant.db.AuthorityKind;
import com.example.demo.constant.db.UserStatusKind;

import lombok.Getter;
import lombok.Setter;

/**
 * 유저갱신화면에 유저정보를 표시하는 클래스
 * 
 */
@Setter
@Getter
public class UserUpdateInfoDto {

	// 갱신대상유저ID
	private String userId;
	
	// 로그인실패횟수
	private int loginFailureCount;
	
//	// 아카운트에 락 날짜
	private LocalDateTime accountLockedTime;
	
	// 어카운트 권한종류
	private AuthorityKind authorityKind;
	
	// 아카운트상태종류
	private UserStatusKind userStatusKind;
	
	// 갱신하는 유저ID
	private String updateUserId;

	// 로그인실패횟수를 리셋할지 여부
	private boolean isResetsLoginFailure;
	
//	public void isResetsLoginFailure() {
//		// TODO Auto-generated method stub
//		this.loginFailureCount = 0;
//	}
//	
}
