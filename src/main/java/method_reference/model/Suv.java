package method_reference.model;

public class Suv extends Car{

	public Suv(String name, String brand) {
		super(name, brand);
	}

	@Override
	public void drive() {
		System.out.println("Driving an SUV " + name + " from " + brand);
	}
}
