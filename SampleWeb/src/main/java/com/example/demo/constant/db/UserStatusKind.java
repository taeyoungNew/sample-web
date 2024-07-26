package com.example.demo.constant.db;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 유저상태정보
 */

@Getter
@AllArgsConstructor
public enum UserStatusKind {
	
	// 사용가능 
	ENBLED(false, "사용가능"),
	
	// 사용불가능
	DISABLED(true, "사용불가능");
	
	// DB등록값
	private boolean isDisabled;
	
	// 화면에 표시되는 설명문 
	private String displayValue;
}

