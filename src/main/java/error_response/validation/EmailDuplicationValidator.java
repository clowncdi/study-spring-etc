package error_response.validation;

import error_response.Member.MemberRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.text.MessageFormat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailDuplicationValidator implements ConstraintValidator<EmailUnique, String> {

	private final MemberRepository memberRepository;

	@Override
	public void initialize(EmailUnique constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		final boolean isExistEmail = memberRepository.existsByEmail(email);
		if (isExistEmail) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(
					MessageFormat.format("{0} 이미 존재하는 이메일 입니다.", email)
			).addConstraintViolation();
		}
		return !isExistEmail;
	}
}
