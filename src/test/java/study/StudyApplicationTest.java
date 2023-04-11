package study;

import java.util.Locale;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
class StudyApplicationTest {

	@Autowired
	MessageSource ms;

	@DisplayName("messages.properties 에 설정해 둔 메시지 호출")
	@Test
	void helloMessage() {
		String result = ms.getMessage("hello", null, null);
		assertThat(result).isEqualTo("안녕");
	}

	@DisplayName("메시지 없으면 예외")
	@Test
	void notFoundMessageCode() {
		assertThatThrownBy(() -> ms.getMessage("no_code", null, null))
				.isInstanceOf(NoSuchMessageException.class);
	}

	@DisplayName("설정이 없으면 기본 메시지")
	@Test
	void notFoundMessageCodeDefaultMessage() {
		String result = ms.getMessage("no_code", null, "기본 메시지", null);
		assertThat(result).isEqualTo("기본 메시지");
	}

	@DisplayName("인자를 가진 메시지")
	@Test
	void argumentMessage() {
		String message = ms.getMessage("hello.name", new Object[] {"Spring"}, null);
		assertThat(message).isEqualTo("안녕 Spring");
	}

	@DisplayName("메시지 국제화 - 기본값 or 한국어")
	@Test
	void defaultLang() {
		assertThat(ms.getMessage("hello", null, null)).isEqualTo("안녕");
		assertThat(ms.getMessage("hello", null, Locale.KOREA)).isEqualTo("안녕");
	}

	@DisplayName("메시지 국제화 - 영어")
	@Test
	void enLnag() {
		assertThat(ms.getMessage("hello", null, Locale.ENGLISH)).isEqualTo("hello");
	}

}
