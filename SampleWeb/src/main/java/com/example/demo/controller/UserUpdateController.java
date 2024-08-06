package com.example.demo.controller;

import java.util.Locale;
import java.util.Optional;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.constant.MessageConst;
import com.example.demo.constant.SessionKeyConst;
import com.example.demo.constant.UrlConst;
import com.example.demo.constant.UserUpdateMessage;
import com.example.demo.constant.ViewNameConst;
import com.example.demo.constant.db.AuthorityKind;
import com.example.demo.constant.db.UserStatusKind;
import com.example.demo.dto.UserUpdateInfoDto;
import com.example.demo.dto.UserUpdateResultDto;
import com.example.demo.entity.UserInfoEntity;
import com.example.demo.form.UserUpdateForm;
import com.example.demo.service.UserUpdateService;
import com.github.dozermapper.core.Mapper;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

/**
 * 유저정보갱신화면 controller클래스
 * 
 * 
 */
@Controller
@RequiredArgsConstructor
public class UserUpdateController {
	
	// 유저 업데이트화면 service클래스
	private final UserUpdateService service;
	
	// 세션오브젝트
	private final HttpSession session;
	
	// Dozer Mapper
	private final Mapper mapper;
	
	// 메세지 소스
	private final MessageSource msgSource;
	
	/**
	 * 이전 리스트 화면에서 선택한 유저의 정보를 화면에 표시
	 * 
	 * @param model 모델(갱신하려는 유저의ID)
	 * @return 유저갱신화면 
	 * @throw Exception(갱신하려는 유저를 찾지 못했을 때)
	 */
	@GetMapping(UrlConst.USER_UPDATE)
	public String updateView(Model model, UserUpdateForm form) throws Exception {
		// 수정할 유저의 ID값을 세션에서 얻는다.
		String userId = (String) session.getAttribute(SessionKeyConst.SELECTED_USER_ID);
		System.out.println("userId= " + userId);
		// 수정할 유저의 정보를 DB에서 가져온다.
		Optional<UserInfoEntity> userInfoOpt = service.searchUserInfo(userId);
		
		// exception => 에러메세지를 던지는걸로 수정 
		if(userInfoOpt.isEmpty()) {
			model.addAttribute("message", msgSource.getMessage(MessageConst.USER_UPDATE_NON_EXISTED_USER_ID, null, Locale.KOREA));
			return ViewNameConst.USER_UPDATE_ERROR;
		}
		
		setupCommonInfo(model, userInfoOpt.get());
		
		return "userUpdate";
		
	}
	
	/**
	 * 화면의 입력정보를 바탕으로 유저정보를 수정
	 * 
	 * @param model 모델
	 * @param form 입력정보
	 * @return 갱신된 화면표시
	 */
	@PostMapping(value = UrlConst.USER_UPDATE, params = "update")
	public String updateUser(Model model, UserUpdateForm form, @AuthenticationPrincipal UserDetails user) {
		System.out.println("유저정보갱신하기");
		// UserUpdateInfoDto클래스로 form을 매핑
		UserUpdateInfoDto updateDto = mapper.map(form, UserUpdateInfoDto.class);
		// 세션에 저장된 업데이트할 유저ID를 updateDto에 저장하기
		updateDto.setUserId((String)session.getAttribute(SessionKeyConst.SELECTED_USER_ID));
		System.out.println("갱신할 유저의 ID : " + updateDto.getUserId());
		// 갱신을 하는 유저의 ID를 set
		updateDto.setUpdateUserId(user.getUsername());
		
		UserUpdateResultDto updateResult = service.updateUserInfo(updateDto);
		UserUpdateMessage updateMessage = updateResult.getUpdateMessage();
		if(updateMessage == UserUpdateMessage.FAILED) {
			model.addAttribute("message", msgSource.getMessage(updateMessage.getMessageId(), null, Locale.KOREA));
			return ViewNameConst.USER_UPDATE_ERROR;
		}
		// 화면에 표시하고 싶은 정보를 갱신한 유저의 정보를 인자로 넘긴다
		setupCommonInfo(model, updateResult.getUpdateUserInfo());
		
		model.addAttribute("isError", false);
		model.addAttribute("message", msgSource.getMessage(updateMessage.getMessageId(), null, Locale.KOREA));
		
		return ViewNameConst.USER_UPDATE;
		
	}
	
	
	
	
	/**
	 * 화면표시에 필요한 공통항목을 설정
	 * 
	 * @param model 모델, UserInfoEntity
	 * @param updateDto 입력
	 */
	private void setupCommonInfo(Model model, UserInfoEntity userInfoEntity) {
		// 갱신화면에 있는 폼에 맞춰 데이터를 
		model.addAttribute("userUpdateForm", mapper.map(userInfoEntity, UserUpdateForm.class));
		model.addAttribute("userUpdateInfoDto", mapper.map(userInfoEntity, UserUpdateInfoDto.class));
		
		model.addAttribute("userStatusKindOptions", UserStatusKind.values());	// 갱신화면의 셀렉트박스에 넣을 유저상태종류
		model.addAttribute("authorityKindOptions", AuthorityKind.values());	// 갱신화면의 셀릭트박스에 넣을 유저권한상태종류
		
	};
	
	
}
