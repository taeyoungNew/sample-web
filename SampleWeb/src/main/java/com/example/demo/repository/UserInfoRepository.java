package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.constant.db.AuthorityKind;
import com.example.demo.constant.db.UserStatusKind;
import com.example.demo.entity.UserInfoEntity;

// JpaRepositoryを継承してJpaRepositoryの機能を使うと定義する
// 첫번째인자는 실제로 사용하는 Entity클래스를 두번째인자는 Id값의 데이터타입을 넣어준다.
// 왜 spring boot의 repository는 인터페이스를 사용할까 
// JpaRepository를 상속받으면 
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfoEntity, String> {

	List<UserInfoEntity> findByUserIdLikeAndUserStatusKindAndAuthorityKind(String userIdParam, UserStatusKind status,
			AuthorityKind authority);

	List<UserInfoEntity> findByUserIdLikeAndAuthorityKind(String userIdParam, AuthorityKind authority);

	List<UserInfoEntity> findByUserIdLikeAndUserStatusKind(String userIdParam, UserStatusKind status);

	List<UserInfoEntity> findByUserIdLike(String userIdParam);




	
	
//	List<UserInfoEntity> findByUserIdLikeAndUserStatus(String userIdParam, UserStatusKind status);
	
//	List<UserInfoEntity> findByUserIdLikeAndUserStatusAndAuthroty(String userIdParam, UserStatusKind status,
//			AuthorityKind authority);

}
