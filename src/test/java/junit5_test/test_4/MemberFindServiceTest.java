package junit5_test.test_4;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import junit5_test.Member;
import junit5_test.MemberFindService;
import junit5_test.MemberRepository;

import static org.assertj.core.api.BDDAssertions.*;

public class MemberFindServiceTest extends IntegrationTestSupport{

	private final MemberRepository memberRepository;
	private final MemberFindService memberFindService;

	public MemberFindServiceTest(MemberRepository memberRepository, MemberFindService memberFindService) {
		this.memberRepository = memberRepository;
		this.memberFindService = memberFindService;
	}

	@DisplayName("회원가입 정상조회 케이스")
	@Test
	void findByName() {
		// given
		memberRepository.save(new Member("junit5"));

		// when
		final Member findMember = memberFindService.findByName("junit5");

		// then
		then(findMember.getName()).isEqualTo("junit5");
	}

	@AfterAll
	void afterAll() {
		final List<Member> members = memberRepository.findAll();
		System.out.println("===============");
		System.out.println("members size: " + members.size());
		System.out.println("===============");
	}
}
