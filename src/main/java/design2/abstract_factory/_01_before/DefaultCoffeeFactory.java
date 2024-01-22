package design2.abstract_factory._01_before;

/**
 * java 8 버전이라면 이렇게 디폴트 메서드를 사용해서 기본 구현을 제공할 수 있다.
 */
public abstract class DefaultCoffeeFactory implements CoffeeFactory {

  @Override
  public void validate(String name, String size) {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("커피 이름은 필수입니다.");
    }
    if (size == null || size.isEmpty()) {
      throw new IllegalArgumentException("커피 사이즈는 필수입니다.");
    }
  }

  @Override
  public void sendToBarista(Coffee coffee) {
    System.out.println(coffee.getName() + " 전달중...");
  }

  @Override
  public void prepareFor(String name) {
    System.out.println(name + " 준비중...");
  }

}
