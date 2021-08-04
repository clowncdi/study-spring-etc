package design.decorator;

public class Audi implements ICar{

	private int price;

	public Audi(int cost) {
		this.price = cost;
	}

	@Override
	public int getPrice() {
		return price;
	}

	@Override
	public void showPrice() {
		System.out.println("audi 의 가격은 " + this.price + " 원 입니다.");
	}
}
