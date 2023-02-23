package event_publisher.member;

import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import event_publisher.EmailSenderService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MemberEventHandler {

	private final EmailSenderService emailSenderService;
	// private final CouponIssueService couponIssueService;

	// @EventListener
	@TransactionalEventListener // 트랜잭션 커밋 이후에 이벤트를 받는다. 트랜잭션 롤백이 되면 실행 취소됨.
	public void memberSignUpEventListener(MemberSignUpEvent dto) {
		emailSenderService.sendSignUpEmail(dto.getMember());
		// couponIssueService.issuedSignUpCoupon(dto.getMember().getId());
	}
}
