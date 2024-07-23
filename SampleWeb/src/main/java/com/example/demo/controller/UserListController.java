package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.constant.AuthorityKind;
import com.example.demo.constant.UrlConst;
import com.example.demo.constant.UserStatusKind;
import com.example.demo.dto.UserListDto;
import com.example.demo.dto.UserListInfoDto;
import com.example.demo.service.UserListService;

import lombok.RequiredArgsConstructor;

/**
 * 유저리스트화면의 Controller
 * 
 */
@Controller
@RequiredArgsConstructor
public class UserListController {
	
	// 유저리스트 서비스
	private final UserListService service;
	
	// 모델의 키 : 유저정보리스트 
	private static final String KEY_USERLIST = "userList";
	
	// 모델의 키 : 유저상태정보 리스트
	private static final String KEY_USER_STATUS_KIND_OPTIONS = "userStatusKindOptions";
	
	// 모델의 키 : 유저권한정보 리스트
	private static final String KEY_AUTHORITY_KIND_OPTIONS = "authorityKindOptions";
	
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
		// entity타입의 유저리스트데이터를 dto타입의 유저리스트데이터로 매핑하고 그 List를 리턴
		List<UserListInfoDto> userInfo = service.editUserList();
		model.addAttribute(KEY_USERLIST, userInfo);
		
		model.addAttribute(KEY_USER_STATUS_KIND_OPTIONS, UserStatusKind.values());
		model.addAttribute(KEY_AUTHORITY_KIND_OPTIONS, AuthorityKind.values());
		
		return "userList";
	}
	
	/**
	 * 유저정보검색
	 * 
	 * 검색조건에 맞는 유저정보를 클라이언트에 반환
	 * 
	 * @param model 모델
	 * @return 화면표시
	 */
	@PostMapping(UrlConst.USER_LIST)
	public String searchUser(Model model, UserListDto form) {
		List<UserListInfoDto> userInfos = service.editUserListByParam(form);
		
		model.addAttribute(KEY_USERLIST, userInfos);
		
		model.addAttribute(KEY_USER_STATUS_KIND_OPTIONS, UserStatusKind.values());
		model.addAttribute(KEY_AUTHORITY_KIND_OPTIONS, AuthorityKind.values());
		
		return "userList";	// 화면전환을 하지않고 그대로 그 화면에 머무를수 있게 userList를 리턴
	}
	
}
