package design2.factory_method._02_after;

/**
 * 팩토리 메소드 패턴
 * 구체적으로 어떤 인스턴스를 만들지는 서브 클래스가 정한다.
 * - 다양한 구현체 (Product)가 있고, 그 중에서 특정한 구현체를 만들 수 있는 다양한 팩토리(Creator)를 제공할 수 있다.
 */
public class Client {

  public static void main(String[] args) {
    Client client = new Client();
    client.print(new AmericanoFactory(), "americano", "tall");
    client.print(new CafeLatteFactory(), "cafeLatte", "tall");
  }

  private void print(CoffeeFactory coffeeFactory, String name, String size) {
    System.out.println(coffeeFactory.orderCoffee(name, size));
  }
}
