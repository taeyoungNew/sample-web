package com.example.demo.Entity;


import java.time.LocalDateTime;

import com.example.demo.constant.AuthorityKind;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// @Entity와 @Table이 import하지 못했던 이유
// JPA dependency의 version이 틀렸었기 때문이다.
@Entity
@Table(name = "user_info")
@Setter
@Getter
@ToString
@AllArgsConstructor
public class UserInfoEntity {
	
	// TBLのフィールド名とEntityのフィールド名が異なる場合は
	// ＠Columnでフィールドの紐付を行う
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)	// id컬럼이 아니면 쓰지말자?
	@Column(name = "user_id")
	private String userId;	// MySql에서는 
	
	// 패스워드
	@Column(name = "password")
	private String password;
	
	// 로그인을 실패한 횟수
	@Column(name = "login_failure_count")
	private int loginFailureCount = 0;
	
	// 어카운트 락 시간
	@Column(name = "account_locked_time")
	private LocalDateTime accountLockeTime;
	
	// 이용가능한지(true : 이용가능)
	@Column(name = "is_disabled")
	private boolean isDisabled;
	
	// 유저의 권한
	@Column(name = "authority")
	private String authority;
	
	public UserInfoEntity() {
		
	}
	
	/**
	 * 로그인 실패횟수를 인크리먼트(증가라는 뜻)
	 * 
	 * @return 로그인실패횟수가 인크리먼트된 UserInfo
	 */
	
	public UserInfoEntity incrementLoginFailureCount() {
		return new UserInfoEntity(userId, password, ++loginFailureCount, accountLockeTime, isDisabled, authority);
	}
	
	/**
	 * 로그인 성공 시 로그인실패정보를 리셋한다.
	 * 
	 * @return 로그인실패정보가 리셋된 UserInfoEntity
	 */
	public UserInfoEntity resetLoginFailureInfo() {
		// 로그인 실패횟수를 0으로 그리고 락걸린시간을 null로 리셋
		return new UserInfoEntity(userId, password, 0, null, isDisabled, authority);
	}
	
	/**
	 * 어카운트에 락 상태를 건다. 
	 * 
	 * @return 로그인 실패횟수, 어카운트락 시간대를 갱신한 UserInfoEntity
	 */
	public UserInfoEntity updateAccountLocked() {
		//  LocalDateTime.now() : 현재시간
		return new UserInfoEntity(userId, password, 0, LocalDateTime.now(), isDisabled, authority);
	}

	public void setAuthority(String string) {
		// TODO Auto-generated method stub
		this.authority = string;
	}

	
}
