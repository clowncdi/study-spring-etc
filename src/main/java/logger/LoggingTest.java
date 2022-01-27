package logger;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggingTest {
	public static Logger logger = Logger.getLogger(LoggingTest.class.getName());

	public static void main(String[] args) {

		logger.log(Level.WARNING, "Warning logging");
		logger.log(Level.INFO, "Info logging");
		logger.log(Level.SEVERE, "Severe logging");

	}
}
