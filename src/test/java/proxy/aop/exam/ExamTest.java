package proxy.aop.exam;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import lombok.extern.slf4j.Slf4j;
import proxy.app.aop.exam.ExamService;
import proxy.app.aop.exam.aop.RetryAspect;
import proxy.app.aop.exam.aop.TraceAspect;

@Slf4j
// @Import(TraceAspect.class)
@Import({TraceAspect.class, RetryAspect.class})
@SpringBootTest
class ExamTest {

	@Autowired
	ExamService examService;

	@Test
	void test() {
		for (int i = 0; i < 5; i++) {
			log.info("client request i={}", i);
			examService.request("data" + i);
		}
	}
}
