package error_response.Member;

import javax.validation.constraints.Email;

import error_response.validation.EmailUnique;
import lombok.Getter;

@Getter
public class SignupRequest {

	@Email
	@EmailUnique
	private String email;

}
