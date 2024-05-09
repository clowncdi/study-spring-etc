package error_response;

import error_response.exception.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sample")
public class SampleController {

	@GetMapping
	public Memeber test(@RequestParam String value) {
		if (value.equals("value")) {
			throw new IllegalArgumentException("System Error Java 8 version");
		} else if (value.equals("none")) {
			throw new IllegalStateException("System Error Java 8 version");
		} else if (value.equals("abc")) {
			throw new EntityNotFoundException(ErrorCode.INVALID_INPUT_VALUE);
		} else {
			return new Memeber("name", "email");
		}
	}

	@PostMapping
	public void test2(@RequestBody @Valid Memeber memeber) {

	}

	public static class Memeber {

		@NotEmpty
		private String name;
		@Email
		private String email;

		public Memeber() {
		}

		public Memeber(String name, String email) {
			this.name = name;
			this.email = email;
		}

		public String getName() {
			return name;
		}

		public String getEmail() {
			return email;
		}
	}
}
