package design.decorator.another.service;

import design.decorator.another.model.Price;

public class BasicPriceProcessor implements PriceProcessor {
	@Override
	public Price process(Price price) {
		return price;
	}
}
