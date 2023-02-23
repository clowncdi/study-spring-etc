package event_publisher.member;

import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import event_publisher.coupon.CouponIssueService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberSignUpService {

	private final MemberReposiotry memberRepository;
	private final CouponIssueService couponIssueService;
	// private final EmailSenderService emailSenderService;
	private final ApplicationEventPublisher eventPublisher;

	@Transactional
	public void signUp(final MemberSignUpRequest request) {
		final Member member = memberRepository.save(request.toEntity()); // 1. member 엔티티 영속화
		// emailSenderService.sendSignUpEmail(member); // 2. 외부 시스템 이메일 호출
		eventPublisher.publishEvent(new MemberSignUpEvent(member));  // 3. 이벤트 퍼블리싱 완료

		couponIssueService.issuedSignUpCoupon(member.getId()); // 3. -> 2. 회원가입 쿠폰 발급 -> 예외 발생, 회원, 쿠폰 모드 롤백, 문제는 회원 가입 이메일 전송 완료

		// try catch로 이벤트 퍼블리싱을 감싸면, 이벤트 퍼블리싱이 실패해도 회원가입은 정상적으로 진행된다. 하지만 좋은 방법은 아니다.
		// try {
		// 	eventPublisher.publishEvent(new MemberSignUpEvent(member));
		// } catch (Exception e) {
		// 	e.printStackTrace();
		// }
	}

	public List<Member> getMembers() {
		return memberRepository.findAll();
	}
}
