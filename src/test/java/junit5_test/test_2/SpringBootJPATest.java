package junit5_test.test_2;

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
public class SpringBootJPATest {

	// @DataJpaTest 를 사용하면, @SpringBootTest 와 비슷하게 테스트 환경을 구성해준다.
	// @SpringBootTest 는 모든 빈을 등록하지만, @DataJpaTest 는 JPA 관련 빈만 등록하기 때문에 속도면에서 더 빠르다.
	// @DataJpaTest 는 @Transactional 이 기본적으로 적용되어있다.
	// @Transactional 은 테스트가 끝나면 자동으로 롤백을 해준다.
	// 하지만, DataJpaTest보다는 통합테스트를 더 풍성하게 작성하는게 좋다.

	private final MemberRepository memberRepository;

	public SpringBootJPATest(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	@Test
	public void member_test() {
		memberRepository.save(new Member("name"));
	}

	@Test
	@Sql("/member-set-up.sql")
	public void sql_test() {
		final List<Member> members = memberRepository.findAll();
		then(members).hasSize(7);
	}

}
