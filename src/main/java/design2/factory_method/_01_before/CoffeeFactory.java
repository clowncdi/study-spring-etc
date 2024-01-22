package design2.factory_method._01_before;


public class CoffeeFactory {

  public static Coffee orderCoffee(String name, String size) {
    // validate
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("커피 이름은 필수입니다.");
    }
    if (size == null || size.isEmpty()) {
      throw new IllegalArgumentException("커피 사이즈는 필수입니다.");
    }

    prepareFor(name);

    Coffee coffee = new Coffee();
    coffee.setName(name);
    coffee.setSize(size);

    // Customizing for specific name
    if (name.equalsIgnoreCase("americano")) {
      coffee.setLogo("Starbucks");
    } else if (name.equalsIgnoreCase("cafe latte")) {
      coffee.setLogo("Hell Coffee");
    }

    // price
    if (size.equalsIgnoreCase("tall")) {
      coffee.setPrice(3000);
    } else if (size.equalsIgnoreCase("grande")) {
      coffee.setPrice(4000);
    } else if (size.equalsIgnoreCase("venti")) {
      coffee.setPrice(5000);
    }

    sendToBarista(coffee);

    return coffee;
  }

  private static void sendToBarista(Coffee coffee) {
    System.out.println(coffee.getName() + " 전달중...");
  }

  private static void prepareFor(String name) {
    System.out.println(name + " 준비중...");
  }

}
