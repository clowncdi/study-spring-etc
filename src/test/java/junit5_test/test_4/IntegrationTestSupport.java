package junit5_test.test_4;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
public class IntegrationTestSupport {

	@Autowired
	protected MockMvc mockMvc;
	@Autowired
	protected ObjectMapper objectMapper;
	@Autowired
	private ResourceLoader resourceLoader;

	@BeforeEach
	void setUp(final WebApplicationContext context) {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
				.alwaysDo(print())
				.build();
	}


	protected String readJson(final String path) throws IOException {
		InputStream inputStream = resourceLoader.getResource("classpath:"+path).getInputStream();
		Scanner scanner = new Scanner(inputStream, "UTF-8").useDelimiter("\\A");
		String jsonString = scanner.hasNext() ? scanner.next() : "";
		scanner.close();
		inputStream.close();
		return jsonString;
	}

}
