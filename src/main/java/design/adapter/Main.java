package design.adapter;

import design.adapter.Electronic110V;
import design.singleton.AClazz;
import design.singleton.BClazz;
import design.singleton.SocketClient;

public class Main {
	public static void main(String[] args) {
		HairDryer hairDryer = new HairDryer();
		connect(hairDryer);

		Cleaner cleaner = new Cleaner();
		Electronic110V adapter = new SocketAdapter(cleaner);
		connect(adapter);

		AirConditioner airConditioner = new AirConditioner();
		Electronic110V airAdapter = new SocketAdapter(airConditioner);
		connect(airAdapter);

	}

	// 콘센트
	public static void connect(Electronic110V electronic110V) {
		electronic110V.powerOn();
	}
}
