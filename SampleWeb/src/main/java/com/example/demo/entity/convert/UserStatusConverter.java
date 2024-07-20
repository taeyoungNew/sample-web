package com.example.demo.entity.convert;

import com.example.demo.constant.UserStatusKind;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;

/**
 * 유저정보, 유저상태종류필드 Converter클래스
 * 
 * 첫번째인자 : Entity가 가지고 있는 유저상태필드
 * 두번째인자 : DB가 가지고 있는 유저상태정보
 * 
 */
@Convert
public class UserStatusConverter implements AttributeConverter<UserStatusKind, Boolean> {
	
	/**
	 * 유저의 상태종류Enum을 매개변수를 받아 사용가능한 ID인지를 Boolean값으로 리턴
	 * 
	 * @param 유저상태종류 Enum
	 * @return 매개변수를 받아 유저상태종류Enum에 보관되어 있는 Boolean
	 * 
	 */
	@Override	// convertToDatabaseColumn : entity에서 넘어온 데이터타입을 DB타입으로 변환하는 메서드
	public Boolean convertToDatabaseColumn(UserStatusKind userStatus) {
		return userStatus.isDisabled();
	}
	
	/**
	 * 권한종류값을 매개변수로 받아 유저상태종류에 Enum으로 변환한다.
	 * 
	 * @param 사용가능여부(사용불가 : true)
	 * @return 매개변수로 받은 사용가능여부에 따라 반대값을 반환 예) true => false false => true
	 * 
	 */
	@Override	// convertToEntityAttribute : DB에서 가져온 데이터를 entity타입으로 변환하는 메서드
	public UserStatusKind convertToEntityAttribute(Boolean isDisabled) {
		return isDisabled ? UserStatusKind.DISABLED : UserStatusKind.ENBLED;
	}
	
	
}

