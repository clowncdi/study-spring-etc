package design2.abstract_factory._02_after;


import design2.abstract_factory._01_before.Americano;
import design2.abstract_factory._01_before.Coffee;
import design2.abstract_factory._01_before.DefaultCoffeeFactory;

public class AmericanoFactory extends DefaultCoffeeFactory {

  private final CoffeePartsFactory coffeePartsFactory;

  public AmericanoFactory(CoffeePartsFactory coffeePartsFactory) {
    this.coffeePartsFactory = coffeePartsFactory;
  }

  @Override
  public Coffee createCoffee() {
    Americano americano = new Americano();
    americano.setIce(coffeePartsFactory.createIce());
    americano.setSugar(coffeePartsFactory.createSugar());
    return americano;
  }
}
