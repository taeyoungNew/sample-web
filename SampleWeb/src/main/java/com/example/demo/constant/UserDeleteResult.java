package com.example.demo.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserDeleteResult {
	// 유저삭제 성공
	SUCCEED(MessageConst.USERIST_DELETE_SUCCEED),
	
	// 유저를 찾을 수 없을 때 실패
	ERROR(MessageConst.USERLIST_NON_EXISTED_USER_ID);

	private String messageId;
	
}
