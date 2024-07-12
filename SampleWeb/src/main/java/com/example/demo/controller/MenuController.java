package com.example.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.constant.AuthorityKind;
import com.example.demo.constant.UrlConst;

/*
 * 메뉴Controller
 */
@Controller
public class MenuController {
	
	/**
	 * 초기표시
	 * @param user 유저정보
	 * @param model 모델
	 * @return 화면표시
	 */
	@GetMapping(UrlConst.MENU)
	public String menuPage(@AuthenticationPrincipal User user, Model model) {
		// hasUserManageAuth : 로그인한 유저가 유저정보를 관리하는것이 가능한가에 대한 결과를 담고있음
		boolean hasUserManageAuth = user.getAuthorities().stream()	// 유저정보에 있는 authorites의 데이터가 1, 2면 false 3이면 true가 된다
				.allMatch(auth -> auth.getAuthority()
						.equals(AuthorityKind.ITEM_AND_USER_MANAGER.getAuthorityKind()));
		
		System.out.println("hasUserManagerAuth = " + hasUserManageAuth);
		// 클라이언트에서는 유저가 유저정보관리가능여부에 따라 버튼을 표시할지 안할지를 결정
		model.addAttribute("hasUserManageAuth", hasUserManageAuth);
		return "menu";
	}
	
	
	
}
