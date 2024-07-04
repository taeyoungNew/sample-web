package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/*
 * 메뉴Controller
 */
@Controller
public class MenuController {
	@GetMapping("/menu")
	public String menuPage() {
		return "menu";
	}
}
