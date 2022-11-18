package design.command.adapter;

import design.command.Robot;

public interface Order {
	public void run(Robot robot);
}
