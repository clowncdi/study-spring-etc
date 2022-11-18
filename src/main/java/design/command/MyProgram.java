package design.command;

import design.command.adapter.MoveBackOrder;

public class MyProgram {
	public static void main(String[] args) {
		RobotKit robotKit = new RobotKit();
		robotKit.addCommand(new MoveForwardCommand(2));
		robotKit.addCommand(new TurnCommand(Robot.Direction.LEFT));
		robotKit.addCommand(new MoveForwardCommand(10));
		robotKit.addCommand(new TurnCommand(Robot.Direction.RIGHT));
		robotKit.addCommand(new PickupCommand());
		robotKit.addCommand(new CommandOrderAdapter(new MoveBackOrder(5)));  // Adapter pattern

		robotKit.start();

		// 2만큼 전진
		// 왼쪽으로 방향전환
		// 10만큼 전진
		// 오른쪽으로 방향전환
		// 앞의 물건 집어들기
		// 왼쪽으로 방향전환
		// 오른쪽으로 방향전환
		// 5만큼 전진
	}
}
