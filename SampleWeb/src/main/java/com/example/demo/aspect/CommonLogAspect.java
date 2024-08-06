package com.example.demo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import ch.qos.logback.classic.Logger;

/**
 * 공통로그출력클래스
 *
 */

@Aspect
@Component
public class CommonLogAspect {
	
	// 로거(Logback)
	// 클래스를 넘긴다.
	private final Logger log = (Logger) LoggerFactory.getLogger(CommonLogAspect.class);
	
	/**
	 * 지정한 메서드의 실행전과 종료후에 로그가 출력된다.
	 * 
	 * <p> 또 예외가 발생했을 때 예외내용을 로그로 출력한다.
	 * @param jp처리를 넣는 장소의 정보 
	 * @return 지정한 메서드의 반환값
	 * 
	 * com.example.demo패키지 안에 정의된 모든 메서드들을 대상으로 한다.
<<<<<<< HEAD
	 * @throws Throwable 메서드실행 시 catch한 예외
=======
	 * @throws Throwable 
>>>>>>> branch 'main' of https://github.com/taeyoungNew/sample-web.git
	 */
	@Around("execution(* com.example.demo..*(..))")
	public Object writeLog(ProceedingJoinPoint jp) throws Throwable {
		Object returnObject = null;
		// 시작 로그를 출력 
		log.info("start : " + jp.getSignature().toString());
		
		try {
			// JoinPoint의 메서드를 실행
			returnObject = jp.proceed();
		} catch (Throwable t) {
			// 에러로그를 출력
			log.error(t.toString());
			// throw t;

		}
		
		// 종료로그를 출력
		log.info("end : " + jp.getSignature().toString());
		
		// 이렇게 정의하지 않으면 Controller클래스의 경우 다음화면으로 이동하지 않는다
		return returnObject;
	}
	
}
