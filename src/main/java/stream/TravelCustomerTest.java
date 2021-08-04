package stream;

import java.util.ArrayList;
import java.util.List;

public class TravelCustomerTest {
	public static void main(String[] args) {
		TravelCustomer customerLee = new TravelCustomer("이순신", 40, 100);
		TravelCustomer customerKim = new TravelCustomer("김유신", 20, 100);
		TravelCustomer customerHong = new TravelCustomer("홍길동", 13, 50);

		List<TravelCustomer> customersList = new ArrayList<>();
		customersList.add(customerLee);
		customersList.add(customerKim);
		customersList.add(customerHong);

		System.out.println("고객 명단 출력");
		customersList.stream().map(c->c.getName()).forEach(s-> System.out.println("s = " + s));

		System.out.println("여행 비용");
		int sum = customersList.stream().mapToInt(c -> c.getPrice()).sum();
		System.out.println("sum = " + sum);

		System.out.println("20세 이상 고객");
		customersList.stream().filter(c->c.getAge() >= 20).map(c->c.getName()).sorted().forEach(s-> System.out.println(
				"s = " + s));
	}
}
