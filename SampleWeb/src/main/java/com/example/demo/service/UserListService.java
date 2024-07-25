package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.constant.ExecuteResult;
import com.example.demo.dto.UserListDto;
import com.example.demo.dto.UserListInfoDto;
import com.example.demo.dto.UserSearchInfoDto;

/**
 * 유저리스트화면 Service인터페이스
 */
@Service
public interface UserListService {
	
	/**
	 * 모든 유저정보테이블을 검색하여 유저리스트화면에 필수데이터로 변환하여 리턴
	 * 
	 * @return 유저정보테이블의 모든 정보
	 */
	public List<UserListInfoDto> editUserList();

	/**
	 * 검색조건에 맞는 유저정보를 클라이언트에 리턴
	 *
	 * @param searchDto
	 * @return
	 */
	public List<UserListInfoDto> editUserListByParam(UserSearchInfoDto searchDto);

	/**
	 * 지정한 ID의 유저정보를 삭제
	 * 
	 * @param userId 
	 * @return 실행결과(에러의 유무) ENUM
	 * 
	 */
	public ExecuteResult deleteUserInfoById(String userId);
}
