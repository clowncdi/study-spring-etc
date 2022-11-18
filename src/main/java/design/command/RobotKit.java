package design.command;

import java.util.ArrayList;

public class RobotKit {

	private Robot robot = new Robot();
	private ArrayList<Command> commands = new ArrayList<>();

	public void addCommand(Command command) {
		this.commands.add(command);
	}

	public void start() {
		for (Command command : commands) {
			command.setRobot(robot);
			command.execute();
		}
	}
}
