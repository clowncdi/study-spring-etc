package event_publisher.member;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import event_publisher.coupon.CouponIssueService;
import event_publisher.EmailSenderService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberSignUpService {

	private final MemberReposiotry memberRepository;
	private final CouponIssueService couponIssueService;
	private final EmailSenderService emailSenderService;

	@Transactional
	public void signUp(final MemberSignUpRequest request) {
		final Member member = memberRepository.save(request.toEntity()); // 1. member 엔티티 영속화
		emailSenderService.sendSignUpEmail(member); // 2. 외부 시스템 이메일 호출
		couponIssueService.issuedSignUpCoupon(member.getId()); // 3. 회원가입 쿠폰 발급
	}

	public List<Member> getMembers() {
		return memberRepository.findAll();
	}
}
