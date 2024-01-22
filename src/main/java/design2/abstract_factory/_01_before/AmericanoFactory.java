package design2.abstract_factory._01_before;


public class AmericanoFactory extends DefaultCoffeeFactory {

  @Override
  public Coffee createCoffee() {
    Americano americano = new Americano();
    americano.setIce(new CubeIce());
    americano.setSugar(new WhiteSugar());
    return americano;
  }
}
