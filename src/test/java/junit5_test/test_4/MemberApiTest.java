package junit5_test.test_4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import junit5_test.Member;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class MemberApiTest extends IntegrationTestSupport {

	@DisplayName("회원가입 테스트 json 관리의 불편")
	@Test
	void test1() throws Exception {
		this.mockMvc.perform(
				post("/members")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"name\":\"junit5\"}")
		).andExpect(status().isOk());
	}

	@DisplayName("회원가입 테스트 객체기반으로 생성해야하는 단점, 테스트 코드에서 밖에 사용하지 않는 코드")
	@Test
	void test2() throws Exception {
		final String requestBody = objectMapper.writeValueAsString(new Member("junit5"));
		this.mockMvc.perform(
				post("/members")
						.contentType(MediaType.APPLICATION_JSON)
						.content(requestBody)
		).andExpect(status().isOk());
	}
	
	@DisplayName("회원가입 테스트")
	@Test
	void test3() throws Exception {
		final String requestBody = readJson("/member-signup.json");
		this.mockMvc.perform(
				post("/members")
						.contentType(MediaType.APPLICATION_JSON)
						.content(requestBody)
		).andExpect(status().isOk());
	}
}
