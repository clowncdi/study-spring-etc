package design.observer2;

import java.util.Random;

public class FairDiceGame extends DiceGame {
	private Random random = new Random();

	@Override
	public void play() {
		int diceNumber = random.nextInt(6) + 1;
		System.out.println("주사위 숫자 : " + diceNumber);

		for (Player player : players) {
			player.update(diceNumber);
		}
	}
}
