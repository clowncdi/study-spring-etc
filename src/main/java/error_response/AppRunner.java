package error_response;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import error_response.Member.Member;
import error_response.Member.MemberRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AppRunner implements ApplicationRunner {

	private final MemberRepository memberRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		memberRepository.save(new Member("aaa@gmail.com"));
		memberRepository.save(new Member("bbb@gmail.com"));
	}
}
