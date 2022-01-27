package design.decorator.another;

import design.decorator.another.model.Price;
import design.decorator.another.service.BasicPriceProcessor;
import design.decorator.another.service.DiscountPriceProcessor;
import design.decorator.another.service.PriceProcessor;
import design.decorator.another.service.TaxPriceProcessor;

public class main {
	public static void main(String[] args) {
		Price originalPrice = new Price("Original price");

		BasicPriceProcessor basicPriceProcessor = new BasicPriceProcessor();
		DiscountPriceProcessor discountPriceProcessor = new DiscountPriceProcessor();
		TaxPriceProcessor taxPriceProcessor = new TaxPriceProcessor();

		PriceProcessor decoratedPriceProcessor = basicPriceProcessor
				.andThen(discountPriceProcessor)
				.andThen(taxPriceProcessor);
		Price decoratedPrice = decoratedPriceProcessor.process(originalPrice);
		System.out.println(decoratedPrice.getPrice());

		PriceProcessor decoratedPriceProcessor2 = basicPriceProcessor
				.andThen(taxPriceProcessor)
				.andThen(price -> new Price(price.getPrice() + ", then apply another procedure"));
		Price decoratedPrice2 = decoratedPriceProcessor2.process(originalPrice);
		System.out.println(decoratedPrice2.getPrice());

	}
}
