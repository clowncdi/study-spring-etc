package design2.factory_method._02_after;

public interface CoffeeFactory {

  default Coffee orderCoffee(String name, String size) {
    validate(name, size);
    prepareFor(name);
    Coffee coffee = createCoffee(name, size);
    sendToBarista(coffee);
    return coffee;
  }

  Coffee createCoffee(String name, String size);

  // java 11 이상부터 private 메서드를 인터페이스에 정의할 수 있다.
  private void validate(String name, String size) {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("커피 이름은 필수입니다.");
    }
    if (size == null || size.isEmpty()) {
      throw new IllegalArgumentException("커피 사이즈는 필수입니다.");
    }
  }

  private static void sendToBarista(Coffee coffee) {
    System.out.println(coffee.getName() + " 전달중...");
  }

  private static void prepareFor(String name) {
    System.out.println(name + " 준비중...");
  }

}
