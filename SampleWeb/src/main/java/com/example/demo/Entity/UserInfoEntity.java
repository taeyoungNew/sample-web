package com.example.demo.Entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

// @Entity와 @Table이 import하지 못했던 이유
// JPA dependency의 version이 틀렸었기 때문이다.
@Entity
@Table(name = "user_info")
@Setter
@Getter
public class UserInfoEntity {
	
	@Id
	// TBLのフィールド名とEntityのフィールド名が異なる場合は
	// ＠Columnでフィールドの紐付を行う
	@Column(name = "login_id")
	private String loginId;	// MySql에서는 
	
	private String password;
}
