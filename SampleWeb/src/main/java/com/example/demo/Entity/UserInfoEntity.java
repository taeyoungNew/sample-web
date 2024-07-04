package com.example.demo.Entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
public class UserInfoEntity {
	
	// TBLのフィールド名とEntityのフィールド名が異なる場合は
	// ＠Columnでフィールドの紐付を行う
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)	// id컬럼이 아니면 쓰지말자?
	@Column(name = "user_id")
	private String userId;	// MySql에서는 
	@Column(name = "password")
	private String password;
}
