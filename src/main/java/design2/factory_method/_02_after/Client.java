package design2.factory_method._02_after;


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
