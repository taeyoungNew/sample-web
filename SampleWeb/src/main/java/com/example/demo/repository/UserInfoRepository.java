package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.UserInfoEntity;

@Repository
// JpaRepositoryを継承してJpaRepositoryの機能を使うと定義する
// 첫번째인자는 실제로 사용하는 Entity클래스를 두번째인자는 Id값의 데이터타입을 넣어준다.
// 왜 spring boot의 repository는 인터페이스를 사용할까 
// JpaRepository를 상속받으면 
public interface UserInfoRepository extends JpaRepository<UserInfoEntity, String> {
	
}
