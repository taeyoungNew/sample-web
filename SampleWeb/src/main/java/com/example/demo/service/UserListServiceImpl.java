package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.UserListInfoDto;
import com.example.demo.entity.UserInfoEntity;
import com.example.demo.repository.UserInfoRepository;
import com.github.dozermapper.core.Mapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserListServiceImpl implements UserListService {

	
	// 유저정보테이블DAO
	private final UserInfoRepository repository;

	// Dozer Mapper
	private final Mapper mapper;
	
	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public List<UserListInfoDto> editUserList() {
		return toUserListInfos(repository.findAll());
	}
	/**
	 * 유저정보Entity의 List를 유저리스트정보DTO의 List로 변환
	 * 
	 * @param userInfos유저정보 entity의 리스트 (DB에서 가지고온 검색결과)
	 * @return 유저리스트 정보DTO의 List
	 */
	private List<UserListInfoDto> toUserListInfos(List<UserInfoEntity> userInfos){
		List<UserListInfoDto> userListInfos = new ArrayList<UserListInfoDto>();
		for(UserInfoEntity userInfo : userInfos) {
			UserListInfoDto userListInfo = mapper.map(userInfo, UserListInfoDto.class);
			userListInfo.setStatus(userInfo.getStatus().getDisplayValue());
			userListInfo.setAuthority(userInfo.getAuthority().getDisplayValue());
			userListInfos.add(userListInfo);
		}
		
		return userListInfos;
	}
}
