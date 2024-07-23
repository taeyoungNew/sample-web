package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.boot.model.internal.Nullability;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserListDto;
import com.example.demo.dto.UserListInfoDto;
import com.example.demo.entity.UserInfoEntity;
import com.example.demo.repository.UserInfoRepository;
import com.example.demo.util.AppUtill;
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
		// 모든 유저정보를 가져와서 toUserListInfo메서드애 넘긴다음 그 결과값을 그대로 리턴한다.
		return toUserListInfos(repository.findAll());
	}
	/**
	 * 유저정보Entity의 List를 유저리스트정보DTO의 List로 변환
	 * 
	 * 흐름 : List<UserInfoEntity>타입으로 넘어온 데이터를 수정해서 담을 같은 타입의 userListInfos를 만들고 
	 * 매개변수 userInfos를 for문으로 돌리면서 mapper로 UserListInfoDto클래스로 매핑을 해주면서 
	 * 매핑을 한 객체를 userListInfos에 add해주고 그 결과를 리턴한다.
	 * 
	 * @param userInfos유저정보 entity의 리스트 (DB에서 가지고온 검색결과)
	 * @return 유저리스트 정보DTO의 List
	 */
	private List<UserListInfoDto> toUserListInfos(List<UserInfoEntity> userInfos){
		List<UserListInfoDto> userListInfos = new ArrayList<UserListInfoDto>();
		for(UserInfoEntity userInfo : userInfos) {
			UserListInfoDto userListInfo = mapper.map(userInfo, UserListInfoDto.class);
			userListInfo.setStatus(userInfo.getUserStatusKind().getDisplayValue());
			userListInfo.setAuthority(userInfo.getAuthorityKind().getDisplayValue());
			userListInfos.add(userListInfo);
		}
		
		return userListInfos;
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public List<UserListInfoDto> editUserListByParam(UserListDto form) {
		System.out.println("form.getAuthorityKind() = " + form.getAuthorityKind());
		// UserInfoEntity에 form을 매핑한다
		UserInfoEntity userInfo = mapper.map(form, UserInfoEntity.class);
		return toUserListInfos(findUserInfoByParam(userInfo));
	}
	
	/**
	 * 유저정보를 가져옴 
	 * 
	 * 유저정보를 조건검색한다.
	 * 
	 * @param UserInfoEntity로 매핑한 form
	 * @return 검색결과
	 */
	private List<UserInfoEntity> findUserInfoByParam(UserInfoEntity userInfo) {
		// addWildcard는 매개변수 앞뒤에 '%'를 붙이는 메서드로 DB의 LIKE기능을 위해 구현
		String userIdParam = AppUtill.addWildcard(userInfo.getUserId());
		
		// 검색조건 : Status와 Authrity가 둘다 있는 경우
		if (userInfo.getUserStatusKind() != null && userInfo.getAuthorityKind() != null) {
			return repository.findByUserIdLikeAndUserStatusKindAndAuthorityKind(userIdParam, userInfo.getUserStatusKind(), userInfo.getAuthorityKind());
		// 검색조건 : Status만 있는경우
		} else if(userInfo.getUserStatusKind() != null) {
			System.out.println("userInfo.getUserStatusKind = " + userInfo.getUserStatusKind());
			return repository.findByUserIdLikeAndUserStatusKind(userIdParam, userInfo.getUserStatusKind());
		// 검색조건 : Authrity만 있는경우
		} else if(userInfo.getAuthorityKind() != null) {
			System.out.println("userInfo.getAuthorityKind = " + userInfo.getAuthorityKind());
			return repository.findByUserIdLikeAndAuthorityKind(userIdParam, userInfo.getAuthorityKind());
		// 검색조건 : 없는 경우
		} else {
			return repository.findByUserIdLike(userIdParam);
		}
	}
}
