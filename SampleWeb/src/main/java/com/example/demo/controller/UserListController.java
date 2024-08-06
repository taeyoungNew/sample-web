package com.example.demo.controller;

import java.text.Normalizer.Form;
import java.util.List;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.constant.SessionKeyConst;
import com.example.demo.constant.UrlConst;
import com.example.demo.constant.UserDeleteResult;
import com.example.demo.constant.ViewNameConst;
//github.com/taeyoungNew/sample-web.git
import com.example.demo.constant.db.AuthorityKind;
import com.example.demo.constant.db.UserStatusKind;
import com.example.demo.dto.UserListInfoDto;
import com.example.demo.dto.UserSearchInfoDto;
import com.example.demo.form.UserListForm;
import com.example.demo.service.UserListService;
import com.example.demo.util.AppUtill;
import com.github.dozermapper.core.Mapper;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
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
	
	
	private final Mapper mapper;
	
	// 메세지 소스
	private final MessageSource messageSource;
	
	// 세션오브젝트
	private final HttpSession session;

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
	public String userListPage(Model model, UserListForm form) {
		// 갱신하고 session에 저장된 데이터를 삭제하는 코드 
		session.removeAttribute(SessionKeyConst.SELECTED_USER_ID);
		
		// entity타입의 유저리스트데이터를 dto타입의 유저리스트데이터로 매핑하고 그 List를 리턴
		List<UserListInfoDto> userInfos = service.editUserList();
		model.addAttribute(KEY_USERLIST, userInfos);
		
		model.addAttribute(KEY_USER_STATUS_KIND_OPTIONS, UserStatusKind.values());
		model.addAttribute(KEY_AUTHORITY_KIND_OPTIONS, AuthorityKind.values());
		
		return ViewNameConst.USER_LIST;
	}
	
	/**
	 * 유저정보검색
	 * 
	 * 검색조건에 맞는 유저정보를 클라이언트에 반환
	 * 
	 * @param model 모델
	 * @return 화면표시
	 */
	// parmas = "~" : request파라미터에 params로 지정된 항목이 있는지 확인한다. 
	// 예) 클라이언트에서 /userList로 보내는 버튼이 여러개 있다고 치면 
	// 컨트롤러에서 어떤 버튼인지 판별하기 위해 클라이언트에서는 name="search"
	// 컨트롤러에서는 params = "search" 로 지정하여 어떤 처리를 원하는 요청인지 구별가능
	@PostMapping(value = UrlConst.USER_LIST, params = "search")
	public String searchUser(Model model, UserListForm form) {
		// UserListDto에 필드가 추가되었기 때문에 삭제할 때 활용하는 DTO를 따로 만들어서 
		// 맵핑을 하고 넘겨준다. 
		UserSearchInfoDto searchDto = mapper.map(form, UserSearchInfoDto.class);
		List<UserListInfoDto> userInfos = service.editUserListByParam(searchDto);
		
		model.addAttribute(KEY_USERLIST, userInfos);
		
		model.addAttribute(KEY_USER_STATUS_KIND_OPTIONS, UserStatusKind.values());
		model.addAttribute(KEY_AUTHORITY_KIND_OPTIONS, AuthorityKind.values());
		
		return ViewNameConst.USER_LIST;	// 화면전환을 하지않고 그대로 그 화면에 머무를수 있게 userList를 리턴
	}
	
	/**
	 * 선택한 유저의 정보를 삭제하고 최신 정보로 다시 반환 
	 * 
	 * @param model 모델 
	 * @param form 입력정보
	 * @return 리다이렉트 URL
	 * 
	 */
	@PostMapping(value = UrlConst.USER_LIST, params = "update")
	public String updateUser(UserListForm form) {
		// 유저갱신화면에 해당 유저ID의 정보를 보내줘야하는데 그 방법으로 Session방식을 선택
		// session에 키, 값의 형태로 SELECTED_USER_ID, form.getSelectedUserId()를 저장
		session.setAttribute(SessionKeyConst.SELECTED_USER_ID, form.getSelectedUserId());
		return AppUtill.doRedirect(UrlConst.USER_UPDATE);
	}
	
	/**
	 * 선택한 유저의 정보를 삭제하고 최신리스트정보를 화면에 리턴
	 * 
	 * @param model 모델
	 * @param form입력정보
	 * @return 화면표시
	 */
	@PostMapping(value = UrlConst.USER_LIST, params = "delete")
	public String deleteUser(Model model, UserListForm form) {
		System.out.println(form.getSelectedUserId());
		UserDeleteResult executeResult = service.deleteUserInfoById(form.getSelectedUserId());
		String messageString = messageSource.getMessage(executeResult.getMessageId(), null, Locale.KOREA);
		
		model.addAttribute("isError", executeResult == UserDeleteResult.ERROR);
		model.addAttribute("message", messageString);
		
		// 삭제 후 form에 저장된 삭제할 유저ID는 불필요함으로 클리어 해준다.
		return searchUser(model, form.clearSelectedUserId());
	}
	
	
}
