package logger;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggingTest {
	public static Logger logger = Logger.getLogger(LoggingTest.class.getName());
	public static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(LoggingTest.class); // Slf4j

	public static void main(String[] args) {

		logger.log(Level.WARNING, "Warning logging");
		logger.log(Level.INFO, "Info logging");
		logger.log(Level.SEVERE, "Severe logging");

		log.trace("trace logging");
		log.debug("debug logging");
		log.info("info logging");
		log.warn("warning logging");
		log.error("error logging");

	}
}
