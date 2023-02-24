package junit5_test.test_3;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import junit5_test.Member;

import static org.assertj.core.api.BDDAssertions.*;

class Test_2 {

	@DisplayName("기존 matcher의 불편한 점")
	@Test
	void test1() {
		MatcherAssert.assertThat("Hello", org.hamcrest.Matchers.is("Hello"));
	}

	@DisplayName("문장 검사")
	@Test
	void test2() {
		final String title = "AssertJ is best matcher";
		then(title)
				.isNotNull()
				.startsWith("AssertJ")
				.endsWith("matcher")
				.contains("best")
				.contains(" ")
				.containsIgnoringCase("BEST")
				.matches(".*best.*")
				.doesNotContain("java")
				.isEqualToIgnoringCase("ASSERTJ IS BEST MATCHER");
	}

	@DisplayName("다양한 기능 제공")
	@Test
	void test3() {
		then(BigDecimal.ZERO).isEqualByComparingTo(BigDecimal.valueOf(0));
		then(" ").isBlank();
		then("").isEmpty();
		then("YWJjZGVmZw==").isBase64();
		then("AA").isIn("AA", "BB", "CC");

		final LocalDate targetDate = LocalDate.of(2023, 2, 24);
		then(targetDate).isBetween(LocalDate.of(2023,2,1), LocalDate.of(2023, 2, 28));
		then(targetDate).isAfter(LocalDate.of(2023, 2, 23));
		then(targetDate).isBefore(LocalDate.of(2023, 2, 25));
		then(targetDate).isAfterOrEqualTo(LocalDate.of(2022, 2, 23));
		then(targetDate).isAfterOrEqualTo(LocalDate.of(2022, 2, 24));
		then(targetDate).isBeforeOrEqualTo(LocalDate.of(2023, 2, 24));
		then(targetDate).isBeforeOrEqualTo(LocalDate.of(2023, 2, 25));
	}

	@DisplayName("collection 검증")
	@Test
	void test4() {
		final List<Member> members = new ArrayList<>();
		members.add(new Member("Kim"));
		members.add(new Member("Joo"));
		members.add(new Member("Jin"));

		then(members).hasSize(3);
		then(members).allSatisfy(member -> {
			System.out.println(member);
			then(member.getName()).isIn("Kim", "Joo", "Jin");
			then(member.getId()).isNull();
		});
	}

	@DisplayName("thenThrownBy 사용법")
	@Test
	void test5() {
		thenThrownBy(() -> new Member(""))
				.isInstanceOf(IllegalArgumentException.class);
		thenThrownBy(() -> {
			throw new IllegalArgumentException("name is empty");
		})
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("name is empty");
	}

	@DisplayName("BDD Style")
	@Test
	void test6() {
		assertThat(10).isEqualTo(10);
		assertThat(true).isTrue();

		then(10).isEqualTo(10);
		then(true).isTrue();
	}
}
