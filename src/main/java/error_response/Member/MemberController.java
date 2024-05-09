package error_response.Member;


import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
public class MemberController {
	
	@PostMapping
	public void create(@RequestBody @Valid SignupRequest request) {
		// 회원가입 로직
	}
}
