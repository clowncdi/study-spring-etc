package design.command;

public class Robot {
	public enum Direction {
		LEFT("왼쪽"),
		RIGHT("오른쪽");
		String value;
		Direction(String value) {
			this.value = value;
		}
		String getValue() {
			return this.value;
		}
	}

	public void moveForward(int space) {
		System.out.println(space + "만큼 전진");
	}

	public void turn(Direction direction) {
		System.out.println(direction.getValue() + "으로 방향전환");
	}

	public void pickup() {
		System.out.println("앞의 물건 집어들기");
	}
}
