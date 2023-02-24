package junit5_test.test_4;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.jdbc.Sql;

import junit5_test.Member;
import junit5_test.MemberRepository;

import static org.assertj.core.api.BDDAssertions.*;

@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@ActiveProfiles("test")
@DataJpaTest
public class RepositoryTest {

	private final MemberRepository memberRepository;

	public RepositoryTest(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	@Test
	void member_test() {
		//given
		memberRepository.save(new Member("junit5"));

		//when
		final Member findMember = memberRepository.findByName("junit5");

		//then
		then(findMember.getName()).isEqualTo("junit5");
	}

	@Test
	@Sql("/member-set-up.sql")
	void sql_test() {
		final List<Member> members = memberRepository.findAll();
		then(members).hasSize(7);
	}
}
