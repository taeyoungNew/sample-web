package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dto.LoginReqDto;

@Controller	// 해당 자반클래스가 Controller의 역할을 한다는 정의
//@RequestMapping("/login")	// localhost:8080/login으로 접속했을 때 이 Controller클래스로 접근
public class LoginController {
	
	private static final String LOGIN_ID = "user";
	private static final String PASSWORD = "pwd";
	
	@GetMapping("/login")
	// Model클래스 : 컨트롤러에서 html로 데이터를 전송하는 클래스 
	// 기본적으로 key와 value의 map형식이다 
	// html에서는 key에 접근하여 value값을 얻는다.
	// 인자로 Model다음 form클래스를 인자로 주면 자동적으로 modeL안에 form데이터를 넣어서 
	// 화면에 반환한다.
	public String LoginPage(Model model, LoginReqDto form) {
		
		
		return "login";	// templates안에 있는 login폴더가 호출
	}
	
	@PostMapping("/login")
	public String Login(Model model, LoginReqDto form) {
		// var키워드 : java10부터 도입되었으며 지역변수의 타입추론을 위한 키워드이다. 
		// 변수선언시 타입을 생략가능하게 한다.
		// 자바스크립트 같넹
		boolean isCorrectUserAuth = form.getLoginId().equals(LOGIN_ID) && form.getPassword().equals(PASSWORD);
		System.out.println(form.toString());
		if(isCorrectUserAuth) {
			return "redirect:/menu";
		} else {
			model.addAttribute("errorMsg", "아이디 또는 패스워드가 일치하지 않습니다.");
			return "login";
		}
	}
}
