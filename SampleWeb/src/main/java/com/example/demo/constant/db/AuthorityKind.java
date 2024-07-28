package com.example.demo.constant.db;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 유저의 권한정보
 * 
 * 
 */
@Getter
@AllArgsConstructor
public enum AuthorityKind {
	
	// 가입된 정보가 부정확할때
	UNKNOWN("", "정보를 찾을 수 없음"),
	
	// 상품정보의 확인이 가능한 권한 
	ITEM_WATCHER("1", "상품정보를 확인가능"),
	
	// 상품정보의 확인 및 갱신이 가능한 권한
	ITEM_MANAGER("2", "상품정보의 확인 및 갱신가능"),
	
	// 상품정보의 확인, 갱신, 전 유저의 정보관리가 가능한 권한
	ITEM_AND_USER_MANAGER("3", "상품확인, 갱신 및 전 유저정보의 관리가 가능");
	
//	private String AuthorityKind;
	
	// 권한 종류
	private String code;
	
	// 화면에 표시할 설명문
	private String displayValue;
	
	/**
	 * Enum 역방향
	 * 권한종류에서 Enum을 역방향으로 데이터를 변환
	 * @param code 데이터베이스에서 얻은 권한종별 코드값
	 * @return 매개변수의 권한종별코드값에 반응하는 Enum, 단 찾지못했다면 UNKNOWN처리
	 * 
	 */
	public static AuthorityKind from(String code) {
		return Arrays.stream(AuthorityKind.values())
				.filter(auth -> auth.getCode().equals(code))
				.findFirst()
				.orElse(UNKNOWN);
	}
	
}
 