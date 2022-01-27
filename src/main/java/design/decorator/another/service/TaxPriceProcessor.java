package design.decorator.another.service;

import design.decorator.another.model.Price;

public class TaxPriceProcessor implements PriceProcessor{
	@Override
	public Price process(Price price) {
		return new Price(price.getPrice() + ", then applied tax");
	}
}
