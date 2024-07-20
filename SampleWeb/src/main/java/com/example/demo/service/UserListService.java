package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.UserListInfoDto;

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
}