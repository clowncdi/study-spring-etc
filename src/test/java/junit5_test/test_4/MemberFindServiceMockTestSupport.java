package junit5_test.test_4;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import junit5_test.Member;
import junit5_test.MemberFindService;
import junit5_test.MemberRepository;

import static org.assertj.core.api.BDDAssertions.*;
import static org.mockito.BDDMockito.given;

class MemberFindServiceMockTestSupport extends MockTestSupport{

	// 외부 인프라
	@InjectMocks
	private MemberFindService memberFindService;

	@Mock
	private MemberRepository memberRepository;

	@Test
	void mock_test() {
		// given
		final Member member = new Member("junit5");
		given(memberRepository.findByName("junit5")).willReturn(member);

		// when
		final Member findMember = memberFindService.findByName("junit5");

		// then
		then(findMember.getName()).isEqualTo("junit5");
	}
}
