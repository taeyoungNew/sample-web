package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.constant.AuthorityKind;
import com.example.demo.constant.UrlConst;
import com.example.demo.constant.UserStatusKind;
import com.example.demo.dto.UserListDto;
import com.example.demo.dto.UserListInfoDto;
import com.example.demo.service.UserListService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserListController {
	
	// 유저리스트 서비스
	private final UserListService service;
	
	// 모델의 키 : 유저정보리스트 
	private static final String KEY_USERLIST = "userList";
	
	// 모델의 키 : 유저상태정보 리스트
	private static final String KEY_USER_STATUS_KIND_STRING = "userStatusKinds";
	
	// 모델의 키 : 유저권한정보 리스트
	private static final String KEY_AUTHORITY_KINDS = "authorityKinds";
	
	/**
	 * 유저관리화면으로 이동 
	 * 
	 * <p> 해당화면으로 이동했을 유저의 어카운트 상태정보, 보유한 권한리스트를 생성한다.
	 * 
	 * @param model 모델
	 * @return 유저관리화면
	 * 
	 */
	@GetMapping(UrlConst.USER_LIST)
	public String userListPage(Model model, UserListDto form) {
//		System.out.println("유저리스트 페이지");
		// 유저
		List<UserListInfoDto> userInfo = service.editUserList();
		model.addAttribute(KEY_USERLIST, userInfo);
		
		model.addAttribute(KEY_USER_STATUS_KIND_STRING, UserStatusKind.values());
//		for(UserStatusKind value : UserStatusKind.values()) {
//			System.out.println("UserStatusKind = " +  value);
//		}
//		
		model.addAttribute(KEY_AUTHORITY_KINDS, AuthorityKind.values());
		
		return "userList";
	}
}
