package design2.abstract_factory._02_after;

import design2.abstract_factory._01_before.Coffee;

/**
 * 추상 팩토리 패턴
 * 서로 관련있는 여러 객체를 만들어주는 인터페이스
 * - 구체적으로 어떤 클래스의 인스턴스를(concrete product)를 사용하는지 감출 수 있다.
 *
 * 팩토리 메소드 패턴과 모양과 효과는 비슷하지만,(둘 다 구체적인 객체 생성 과정을 추상화한 인터페이스를 제공한다)
 * 관점이 다르다.
 * 팩토리 메소드 패턴은 "팩토리를 구현하는 방법(inheritance)"에 초점을 둔다.
 * 추상 팩토리 패턴은 "팩토리를 사용하는 방법(composition)"에 초점을 둔다.
 * 목적이 조금 다르다.
 * 팩토리 메소드 패턴은 구체적인 객체 생성 과정을 하위 또는 구체적인 클래스로 옮기는 것이 목적.
 * 추상 팩토리 패턴은 관련 있는 여러 객체를 구체적인 클래스에 의존하지 않고 만들 수 있게 해주는 것이 목적.
 */
public class Client {

  public static void main(String[] args) {
    AmericanoFactory slushIceAmericanoFactory = new AmericanoFactory(new SlushIceAmericanoPartsFactory());
    Coffee slushCoffee = slushIceAmericanoFactory.createCoffee();
    System.out.println(slushCoffee.getIce().getClass());
    System.out.println(slushCoffee.getSugar().getClass());

    AmericanoFactory cubeIceAmericanoFactory = new AmericanoFactory(new CubeIceAmericanoPartsFactory());
    Coffee cubeCoffee = cubeIceAmericanoFactory.createCoffee();
    System.out.println(cubeCoffee.getIce().getClass());
    System.out.println(cubeCoffee.getSugar().getClass());
  }

}
