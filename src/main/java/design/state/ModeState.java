package design.state;

public interface ModeState {
	public void toggle(ModeSwitch modeSwitch);
}

class ModeStateLight implements ModeState {
	@Override
	public void toggle(ModeSwitch modeSwitch) {
		System.out.println("From Light to Dark");
		//화면을 어둡게 하는 코드
		modeSwitch.setState(new ModeStateDark());
	}
}

class ModeStateDark implements ModeState {
	@Override
	public void toggle(ModeSwitch modeSwitch) {
		System.out.println("From Dark to Light");
		//화면을 밝게 하는 코드
		modeSwitch.setState(new ModeStateLight());
	}
}
