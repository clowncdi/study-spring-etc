package design.state;

/**]
 * 전략 패턴은 한 번 인스턴스를 생성하고 나면, 상태가 거의 바뀌지 않는 경우에 사용
 * 상태 패턴은 한 번 인스턴스를 생성하고 난 뒤, 상태를 바꾸는 경우가 빈번할 경우에 사용
 */
public class MyProgram {
	public static void main(final String[] args) {
		final ModeSwitch modeSwitch = new ModeSwitch();

		modeSwitch.onSwitch(); // From Light to Dark
		modeSwitch.onSwitch(); // From Dark to Light
		modeSwitch.onSwitch(); // From Light to Dark
		modeSwitch.onSwitch(); // From Dark to Light
	}
}
