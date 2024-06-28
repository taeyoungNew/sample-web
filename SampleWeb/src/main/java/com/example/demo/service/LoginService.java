package com.example.demo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.Entity.UserInfoEntity;
import com.example.demo.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;

@Service
// @RequiredArgsConstructor는 lombok이 제공하는 어노테이션으로 
// 클래스의 final필드나 @Nonnull어노테이션이 적용된 필드에 대한 생성자를 자동으로 생성해준다.
@RequiredArgsConstructor
public class LoginService {
	private final UserInfoRepository repository;
	
	// repositoryの戻り値はOptional
	// Optional타입은 null을 반환하면 에러가 발생할 가능성이 높은 상황에서
	// 메소드의 반환타입으로 Optional을 사용하자는 것이 주된목적
	// Optional타입의 변수의 값은 절대 null이어서는 안되며 항상 Optional인스턴스를 가리켜야한다.
	public Optional<UserInfoEntity> searchUserById(String loginId) {
		return repository.findById(loginId);
	}
	
}
