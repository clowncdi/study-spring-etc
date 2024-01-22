package design2.abstract_factory._01_before;

public interface CoffeeFactory {

  default Coffee orderCoffee(String name, String size) {
    validate(name, size);
    prepareFor(name);
    Coffee coffee = createCoffee();
    sendToBarista(coffee);
    return coffee;
  }

  Coffee createCoffee();

  void validate(String name, String size);

  void sendToBarista(Coffee coffee);

  void prepareFor(String name);

}
