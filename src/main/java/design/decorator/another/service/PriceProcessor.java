package design.decorator.another.service;

import design.decorator.another.model.Price;

@FunctionalInterface
public interface PriceProcessor {
	Price process(Price price);

	default PriceProcessor andThen(PriceProcessor next) {
		return price -> next.process(process(price));
	}
}
