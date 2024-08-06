package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.constant.UserUpdateMessage;
import com.example.demo.dto.UserUpdateInfoDto;
import com.example.demo.dto.UserUpdateResultDto;
import com.example.demo.entity.UserInfoEntity;
import com.example.demo.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;

/**
 * 유저정보갱신화면 service
 * 
 */
@Service
@RequiredArgsConstructor
public class UserUpdateServiceImpl implements UserUpdateService {

	private final UserInfoRepository repository;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<UserInfoEntity> searchUserInfo(String userId) {
		return repository.findById(userId);
	}

	@Override
	public UserUpdateResultDto updateUserInfo(UserUpdateInfoDto userUpdateInfo) {
		UserUpdateResultDto userUpdateResult = new UserUpdateResultDto();

		// 현재등록정보를 가져온다.
		Optional<UserInfoEntity> updateInfoOpt = repository.findById(userUpdateInfo.getUserId());
		if (updateInfoOpt.isEmpty()) {
			userUpdateResult.setUpdateMessage(UserUpdateMessage.FAILED);
			return userUpdateResult;
		}

		// 화면의 입력정보 등을 세트
		UserInfoEntity updateInfo = updateInfoOpt.get();
		updateInfo.setUserStatusKind(userUpdateInfo.getUserStatusKind());
		updateInfo.setAuthorityKind(userUpdateInfo.getAuthorityKind());
		if (userUpdateInfo.isResetsLoginFailure()) {
			updateInfo.setLoginFailureCount(0);
			updateInfo.setAccountLockeTime(null);
		}
		updateInfo.setUpdateTime(LocalDateTime.now());
		updateInfo.setUpdateUser(userUpdateInfo.getUpdateUserId());

		try {
			repository.save(updateInfo);
		} catch (Exception e) {
			userUpdateResult.setUpdateMessage(UserUpdateMessage.FAILED);
			return userUpdateResult;
		}
		userUpdateResult.setUpdateUserInfo(updateInfo);
		userUpdateResult.setUpdateMessage(UserUpdateMessage.SUCCEED);

		return null;
	}

}
