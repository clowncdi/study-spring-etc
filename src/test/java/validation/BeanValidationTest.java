package validation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;
import org.junit.jupiter.api.Test;

class BeanValidationTest {

	@Test
	void beanValidation() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		Item item = new Item();
		item.setItemName(" ");
		item.setPrice(0);
		item.setQuantity(10000);

		Set<ConstraintViolation<Item>> validations = validator.validate(item);
		for (ConstraintViolation<Item> validation : validations) {
			System.out.println("validation = " + validation);
			System.out.println("validation = " + validation.getMessage());
		}
	}
}
