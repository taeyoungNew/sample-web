package com.example.demo.constant;

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
	
	// 상품정보의 확인이 가능한 권한 
	ITEM_WATCHER("1"),
	// 상품정보의 확인 및 갱신이 가능한 권한
	
	ITEM_MANAGER("2"),
	// 상품정보의 확인, 갱신, 전 유저의 정보관리가 가능한 권한
	
	ITEM_AND_USER_MANAGER("3");
	
	private String AuthorityKind;
	
	
	
}
