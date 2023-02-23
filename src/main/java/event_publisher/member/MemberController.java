package event_publisher.member;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

	private final MemberSignUpService memberSignUpService;

	@PostMapping
	public void signUp(@RequestBody MemberSignUpRequest request) {
		memberSignUpService.signUp(request);
	}

	@GetMapping
	public List<Member> getMembers() {
		return memberSignUpService.getMembers();
	}
}
