package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.constant.UrlConst;

/*
 * 메뉴Controller
 */
@Controller
public class MenuController {
	@GetMapping(UrlConst.MENU)
	public String menuPage() {
		return "menu";
	}
}
