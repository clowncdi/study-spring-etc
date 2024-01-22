package design2.abstract_factory._02_after;

import design2.abstract_factory._01_before.CubeIce;
import design2.abstract_factory._01_before.WhiteSugar;

public class CubeIceAmericanoPartsFactory implements CoffeePartsFactory {

  @Override
  public Ice createIce() {
    return new CubeIce();
  }

  @Override
  public Sugar createSugar() {
    return new WhiteSugar();
  }
}
