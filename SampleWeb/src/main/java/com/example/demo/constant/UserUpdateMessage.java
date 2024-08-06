package com.example.demo.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 유저갱신결과메세지를 반환하는 클래스
 * 
 * 
 */
@Getter
@AllArgsConstructor
public enum UserUpdateMessage {
	// 갱신성공
	SUCCEED(MessageConst.USER_UPDATE_SUCCEED),
	// 갱신실패
	FAILED(MessageConst.USER_UPDATE_FAILED);
	
	// 메세지ID
	private String messageId;
}
