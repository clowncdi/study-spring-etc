package proxy.app.aop.exam;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import proxy.app.aop.exam.annotation.Trace;

@Service
@RequiredArgsConstructor
public class ExamService {

	private final ExamRepository examRepository;

	@Trace
	public void request(String itemId) {
		examRepository.save(itemId);
	}
}
