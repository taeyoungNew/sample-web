package com.example.demo.dto;

import com.example.demo.constant.UserUpdateMessage;
import com.example.demo.entity.UserInfoEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 유저를 갱신한 후 결과를 가져오는 클래스
 * 
 * 
 */
@Getter
@Setter
public class UserUpdateResultDto {
	// 유저의 갱신결과
	private UserInfoEntity updateUserInfo;
	
	// 유저갱신결과 메세지ENUM
	private UserUpdateMessage updateMessage;
}
