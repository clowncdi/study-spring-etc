package design2.abstract_factory._02_after;

public class SlushIceAmericanoPartsFactory implements CoffeePartsFactory {

  @Override
  public Ice createIce() {
    return new SlushIce();
  }

  @Override
  public Sugar createSugar() {
    return new BlueSugar();
  }
}
