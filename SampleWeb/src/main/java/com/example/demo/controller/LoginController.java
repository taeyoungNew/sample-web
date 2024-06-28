package com.example.demo.controller;

import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.Entity.UserInfoEntity;
import com.example.demo.constant.ErrorMassageConst;
import com.example.demo.dto.LoginReqDto;
import com.example.demo.service.LoginService;
//import com.example.demo.util.AppUtill;

import lombok.RequiredArgsConstructor;

/**
 * 
 * 로그인화면Controller
 * 
 */
@Controller	// 해당 자반클래스가 Controller의 역할을 한다는 정의
//@RequestMapping("/login")	// localhost:8080/login으로 접속했을 때 이 Controller클래스로 접근
@RequiredArgsConstructor
public class LoginController {
	// 로그인Service
	private final LoginService loginService;
	
	// PasswordEncoder
	private final PasswordEncoder passwordEncoder;
	
	// message source
	@Autowired
	private final MessageSource messageSource;
	
	// Model클래스 : 컨트롤러에서 html로 데이터를 전송하는 클래스 
	// 기본적으로 key와 value의 map형식이다 
	// html에서는 key에 접근하여 value값을 얻는다.
	// 인자로 Model다음 form클래스를 인자로 주면 자동적으로 modeL안에 form데이터를 넣어서 
	// 화면에 반환한다.
	@GetMapping("/login")
	public String LoginPage(Model model, LoginReqDto form) {
		
		/**
		 * 초기화면 
		 * @param model
		 * @param form 입력정보
		 * @return 표시화면
		 */
		return "login";	// templates안에 있는 login폴더가 호출
	}
	
	/**
	 * 
	 * @param model
	 * @param form
	 * @return 화면표시
	 */
	@PostMapping("/login")
	public String Login(Model model, LoginReqDto form) {
		Optional<UserInfoEntity> userInfo = loginService.searchUserById(form.getLoginId());
		// isPresent() : 값이 있는지의 여부를 boolean으로 반환
		// userInfo는 Optional타입이기 때문에 
		boolean isCorrectUserAuth = userInfo.isPresent() 
				// 첫번째 인자는 Hash화 되지 않은 패스워드, 두번째 인자는 Hash화 되어있는 패스워드
				&& passwordEncoder.matches(form.getPassword(),userInfo.get().getPassword());
		if(isCorrectUserAuth) {
			return "redirect:/menu";
		} else {
			// message의 key를 모은 클래스를 만들어서 종합적으로 관리하는것을 지향하자
			String errorMsgString = messageSource.getMessage(ErrorMassageConst.LOGIN_WRONG_INPUT, null, Locale.KOREA);
//			String errorMsgString = AppUtill.getMessage(messageSource, ErrorMassageConst.LOGIN_WRONG_INPUT);
			model.addAttribute("errorMsg", errorMsgString);
			return "login"; 
		}
		// var키워드 : java10부터 도입되었으며 지역변수의 타입추론을 위한 키워드이다. 
		// 변수선언시 타입을 생략가능하게 한다.
		// 자바스크립트 같넹
//		boolean isCorrectUserAuth = form.getLoginId().equals(LOGIN_ID) && form.getPassword().equals(PASSWORD);
	}
}
