package com.example.demo.dto;

import com.example.demo.constant.db.AuthorityKind;
import com.example.demo.constant.db.UserStatusKind;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 유저리스트화면검색용 DTO클래스
 * 
 */

@Setter
@Getter
public class UserSearchInfoDto {
	// 유저ID
	private String userId;
	
	// 어카운트 상태
	private UserStatusKind userStatusKind;
	
	// 유저의 권한정보
	private AuthorityKind authorityKind;
	
}
