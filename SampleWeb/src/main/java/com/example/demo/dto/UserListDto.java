package com.example.demo.dto;

import org.hibernate.validator.constraints.Length;

import com.example.demo.constant.AuthorityKind;
import com.example.demo.constant.UserStatusKind;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 유저리스트
 */
@Setter
@Getter
@ToString
public class UserListDto {
	// 유저ID
	@Length(min=8, max=20)
	private String userId;
	
	// 어카운트 상태종류
	private UserStatusKind userStatusKind;
	
	// 유저의 권한 종류
	private AuthorityKind authorityKind;
	
	// 유저리스트에서 선택한 유저의 ID
	private String selectedUserId;
	
	/**
	 * 유저리스트에서 선택한 유저ID를 삭제한다.
	 * 
	 * @return 유저리스트에서 선택한 유저ID를 삭제한 뒤에 null을 반환해서 selectedUserId를 비워준다.
	 * 
	 */
	public UserListDto clearSelectedUserId() {
		this.selectedUserId = null;
		
		return this;
	}
	
	
}
