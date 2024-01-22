package design2.factory_method._02_after;


public class AmericanoFactory implements CoffeeFactory {

  @Override
  public Coffee createCoffee(String name, String size) {
    return new Americano(name, size);
  }

}
