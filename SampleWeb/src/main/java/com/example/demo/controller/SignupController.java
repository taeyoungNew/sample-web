package com.example.demo.controller;

import java.util.Locale;
import java.util.Optional;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.constant.MessageConst;
import com.example.demo.constant.SignupMessage;
import com.example.demo.constant.UrlConst;
import com.example.demo.constant.ViewNameConst;
import com.example.demo.entity.UserInfoEntity;
import com.example.demo.form.SignupReqForm;
import com.example.demo.service.SignupService;
import com.example.demo.service.SignupServiceImpl;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class SignupController {
	// 회원가입 service
	private final SignupService signupService;
	
	// 메세지 소스
	private final MessageSource messageSource;
	/**
	 * 
	 * @param mode
	 * @param form
	 * @return 회원가입화면표시
	 */
	
	@GetMapping(UrlConst.SIGNUP)
	public String signupPage(Model model, SignupReqForm form) {
		return ViewNameConst.SIGNUP;
	}
	
	
	/**
	 * @param model 모델
	 * @param form 입력정보
	 * @param bindingResult 입력체크결과
	 * @return 화면
	 */
	@PostMapping(UrlConst.SIGNUP)
	// @Validated : Validation을 사용하기 위해서는 클라이언트에서 받는 form앞에 @Validated어노테이션을 붙인다.
	// BindingResult : @Validated가 붙은 폼의 입력체크결과를 가져온다.반드시 form 바로 뒤에 정의를 하지않으면 
	// Validated결과를 가져올수 없다.
	public void signup(Model model, @Validated SignupReqForm form, BindingResult bidingResult) {
		if(bidingResult.hasErrors()) {
//			String formErrMessage = messageSource.getMessage(MessageConst.FORM_ERROR , null, Locale.KOREA);
//			model.addAttribute("message", formErrMessage);
//			model.addAttribute("isError", true);
			editGuideMessage(model, MessageConst.FORM_ERROR, true);
			return;	// Validation에러가 뜨면 return만 해줘도 Tymeleaf에서 받을 수 있음 
		}
		Optional<UserInfoEntity> userInfoOpt = signupService.resistUserInfo(form);
		// 	judgeMessageKey를 Enum으로 바꿨기때문에 
		//	1. 기존의 아이디가 있으면 '이미 존재하는 아이디입니다.'라는 에러문구와 true값을 가진 isError를 반환하는 EXISTED_LOGIN_ID를 리턴하고
		// 	2. 기존 아이디가 없으면 '회원가입이 완료되었습니다.'라는 에러문구과 false값을 가진 isError를 반환하는 SUCCESS를 리턴한다.
		// 	3. messageSource.getMessage(String타입의 signupEnum.getMessage()를 인자로 넣어주고
		// 	4. boolean타입의 isError변수를 생성하여 signupEnum.isError()의 값을 할당한다.
		SignupMessage signupEnum = judgeMessageKey(userInfoOpt);
//		String massage = messageSource.getMessage(signupEnum.getMessage() , null, Locale.KOREA);
		boolean isError = signupEnum.isError();
//		model.addAttribute("message", massage);
//		model.addAttribute("isError", isError);
		editGuideMessage(model, signupEnum.getMessage(), isError);
	}
	
	/**
	 * 화면에 표시하는 가이드메세지를 설정
	 * @param moedel 모델
	 * @param messageId 메세지 ID
	 * @param isError 에러의 유무
	 * 
	 */
	private void editGuideMessage(Model model, String messageId, boolean isError) {
		String messageString = messageSource.getMessage(messageId, null, Locale.KOREA);
		model.addAttribute("message", messageString);
		model.addAttribute("isError", isError);
	}
	
	/**
	 * 유저회원가입 결과메세지의 키를 반환하는 메서드
	 * @param userInfoOpt
	 * @return mesasge key
	 */
	private SignupMessage judgeMessageKey(Optional<UserInfoEntity> userInfoOpt) {
		if(userInfoOpt.isEmpty()) {
			return SignupMessage.EXISTED_LOGIN_ID;
		} else {
			return SignupMessage.SUCCESS;
		}
	}
	
	
	
} 
