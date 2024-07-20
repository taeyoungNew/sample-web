package com.example.demo.entity.convert;

import com.example.demo.constant.AuthorityKind;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class UserAuthorityConverter implements AttributeConverter<AuthorityKind, String>{

	/**
	 * 매개변수로 받은 권한종류Enum을 권한정류별 코드값으로 변환
	 * 
	 * @param 권한종별Enum
	 * @return 매개변수를 받은 권한종류별 Enum에 보관되어있는 String값
	 */
	@Override
	public String convertToDatabaseColumn(AuthorityKind authorityKind) {
		
		return authorityKind.getCode();
	}

	/**
	 * 매개변수로 받은 권한종류별 코드값을 권한종류별 Enum으로 변환
	 * 
	 * @param 권한종류의 코드값
	 * @return 매개변수로 받은 권한종류별 코드값에서 받대값의 Enum
	 */
	@Override
	public AuthorityKind convertToEntityAttribute(String value) {
		
		return AuthorityKind.from(value);
	}
}
