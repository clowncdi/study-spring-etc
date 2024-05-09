package error_response.Member;


import error_response.validation.EmailUnique;
import jakarta.validation.constraints.Email;
import lombok.Getter;

@Getter
public class SignupRequest {

	@Email
	@EmailUnique
	private String email;

}
