package com.example.demo.authentication;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.demo.entity.UserInfoEntity;
import com.example.demo.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;


/**
 * 유저정보 생성
 */
@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	// 유저정보테이블 Repository
	private final UserInfoRepository userInfoRepository;

	// 로그인 최대 실패 횟수
	@Value("${security.locking-border-count}")	// application.properties에 있는 프로퍼티를 연결
	private int lockingBorderCount;

	// 어카운트 락 시간
	@Value("${security.locking-time}")
	private int lockingTime;

	// String username = 로그인아이디를 받는다.
	// 디포트설정으로는 name = username의 input값을 받지만
	// 해당 클래스를 구현함으로써 username = userId의 값이 들어오게 되는 것이다.
	/**
	 * @param username userId
	 * @throws UsernameNotFoundException
	 */
	@Override // String name = 로그인아이디를 받음
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserInfoEntity userInfo = userInfoRepository.findById(username)
				// 유저의 ID를 찾지못했을 때의 에러처리 : UsernameNotFoundException
				.orElseThrow(() -> new UsernameNotFoundException(username));
		LocalDateTime accountLockedTime = userInfo.getAccountLockeTime();
		boolean isAccountLocked = accountLockedTime != null
				&& accountLockedTime.plusHours(lockingBorderCount).isAfter(LocalDateTime.now());

		// User클래스 : Spring Security에서 제동하는 디폰트 사용자모델로
		// UserDetails인터페이스를 구현하고 있어 사용자의 인증정보와 권한정보를 제공한다.
		return User.withUsername(userInfo.getUserId()) // User
				.password(userInfo.getPassword())
//				.roles("USER")
				.authorities(userInfo.getAuthorityKind().getCode())	// 유저의 권한정보를 저장 tymeleaf에서는 hasUserManageAuth로 받는다. 
				.disabled(userInfo.getUserStatusKind().isDisabled())
				.accountLocked(isAccountLocked)
				.build();
	}

	/**
	 * 인증실패시 로그인 실패횟수를 계산하고 락걸린 시간을 갱신
	 * 로그인실패시 발생하는 이벤트를 받는 메서드
	 * AuthenticationFailureBadCredentialsEvent가 받음
	 * 
	 * @param event 이벤트 정보
	 */
	@EventListener
	public void handle(AuthenticationFailureBadCredentialsEvent event) {
		System.out.println("로그인 실패");
		String loginId = event.getAuthentication().getName();
		//	ifPresent()는 Optional 객체가 값을 가지고 있으면 실행 or 값이 없으면 넘어감
		// loginId를 찾고 그 값이 있으면 ifPresent메서드가 다음으로 넘긴다.
		userInfoRepository.findById(loginId).ifPresent(userInfo-> {
			System.out.println("로그인횟수 늘리기");
			// 로그인 한번 실패시 로그인한 아이디의 failureCount컬럼에 1씩 추가한다.
			userInfoRepository.save(userInfo.incrementLoginFailureCount());
			
			// 총 실패한 횟수를 확인하고 최대 실패횟수와 같을 때 해당 아이디를 락 상태로 만든다. 
			boolean isReachFailureCount = userInfo.getLoginFailureCount() == lockingBorderCount;
			if (isReachFailureCount) {
				// 락 상태인지 판단하는 것은 accountLocked에 락걸린 시간 데이터를 저장
				userInfoRepository.save(userInfo.updateAccountLocked());
			}
		});
	}

	/**
	 * 인증성공시 해당 login_id의 로그인 실패횟수를 리셋한다.
	 * 
	 * @param event 이벤트 정보
	 */
	@EventListener
	public void handle(AuthenticationSuccessEvent event) {
		System.out.println("로그인 성공");
		String loginId = event.getAuthentication().getName();
		userInfoRepository.findById(loginId).ifPresent(userInfo -> {
			userInfoRepository.save(userInfo.resetLoginFailureInfo());
		});
	}
	
}
