package design2.factory_method._01_before;


public class Client {

  public static void main(String[] args) {
    Client client = new Client();

    Coffee cafeLatte = CoffeeFactory.orderCoffee("cafe latte", "tall");
    System.out.println(cafeLatte);

    Coffee americano = CoffeeFactory.orderCoffee("americano", "grande");
    System.out.println(americano);

    }
}
