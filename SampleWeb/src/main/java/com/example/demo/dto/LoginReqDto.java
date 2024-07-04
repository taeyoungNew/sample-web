package com.example.demo.dto;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//	@Data : 
// 	@Getter, @Setter, @ToString, 
// 	@EqualsAndHasCode, @RequiredArgsConstructor를 모두 가지고 있는 어노테이션
// 	사용을 지양하는 이유 :
// 	1. 무분별한 @Setter남용 : 변경하면 안되는 값을 변경가능한 값이라고 착각을 줌 예) Id
// 	2. @ToString양방향 연관관계시 순환참조 :
// 	JPA에서 N:1관계 양방향 참조시 @ToString을 사용하면 순환참조 문제가 발생 
// 	Project와 Member라는 도메인이 있고 Project와 Member가 1:N관계를 맺었다고 가정하면 
//	Member toString이 호출되면서 Stack에 메모리가 쌓이고 연관관계를 정의했으므로 
//	Member의 Project가 불려지면서 Project toString이 호출되고 
//	다시 Project 내의 Member를 호출하면서 또 toString을 호출하면서 
//	Project 를 호출하고 그렇게 무한 참조가 걸리고 Stack 메모리가 차면서 StackOverFlowError가 걸리게 된다.
@Setter
@Getter
@ToString
public class LoginReqDto {
	
	@Length(min = 8, max = 20)	// 변수의 값의 최소길이와 최대길이
	private String userId;
	
	@Length(min = 5, max = 20)
//	@Max(20)
	private String password;
	
}
