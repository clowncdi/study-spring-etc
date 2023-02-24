package junit5_test;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberFindService {
	private final MemberRepository memberRepository;

	public Member findById(Long id) {
		return memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found"));
	}

	public Member findByName(String name) {
		return memberRepository.findByName(name);
	}
}
