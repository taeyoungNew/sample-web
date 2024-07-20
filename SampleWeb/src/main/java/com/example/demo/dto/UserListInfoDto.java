package com.example.demo.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserListInfoDto {
	
	// 유저ID
	private String userId;
	
	// 로그인 실패횟수
	private int loginFailureCount;
	
	// 아카운트락걸린 날짜
	private LocalDateTime accountLockedTime;
	
	// 아카운트 상태
	private String status;
	
	// 유저의 권한
	private String authority;
	
	// ID생성날짜
	private LocalDateTime createTime;
	
	// ID갱신날짜
	private LocalDateTime updateTime;
	
	// 최신갱신한 유저
	private String updateUser;

}
