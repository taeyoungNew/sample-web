package com.example.demo.service;

import java.util.Optional;

import com.example.demo.dto.UserUpdateInfoDto;
import com.example.demo.dto.UserUpdateResultDto;
import com.example.demo.entity.UserInfoEntity;

/**
 * 유저리스트에서 선택한 유저의 정보를 갱신합니다.
 * 
 * @param userUpdateDto
 * @return 가입정보(유저정보Entity)
 */
public interface UserUpdateService {
	/**
	 * 유저ID로 유저정보 찾기
	 * @param userId
	 * @return
	 */
	public Optional<UserInfoEntity> searchUserInfo(String userId);

	/**
	 * 
	 */
	public UserUpdateResultDto updateUserInfo(UserUpdateInfoDto userUpdateInfo);
	
	
}
