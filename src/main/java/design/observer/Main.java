package design.observer;

public class Main {
	public static void main(String[] args) {
		Button button = new Button("버튼");
		button.addListener(System.out::println);
		button.click("메시지 전달 : click1");
		button.click("메시지 전달 : click2");
		button.click("메시지 전달 : click3");
		button.click("메시지 전달 : click4");
	}
}
