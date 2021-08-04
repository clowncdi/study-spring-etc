package generic;

public class GenericPrinterTest {
	public static void main(String[] args) {
		Powder powder = new Powder();
		GenericPrinter<Powder> powderPrinter = new GenericPrinter<>() ;
		powderPrinter.setMaterail(powder);
		Powder p = powderPrinter.getMaterail();
		String s = powderPrinter.toString();
		System.out.println("s = " + s);


	}
}
