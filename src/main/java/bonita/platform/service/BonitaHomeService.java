package bonita.platform.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import restx.factory.Component;

@Component
public class BonitaHomeService {
	Logger logger = LoggerFactory.getLogger(getClass());

	public BonitaHomeService() {
		initBonitaHome();
	}

	private void initBonitaHome() {

		logger.info("BonitaHomeService is using with bonita.home value:"
				+ System.getProperty("bonita.home"));
		// System.out.println("BonitaHomeService is using with bonita.home value:"
		// + System.getProperty("bonita.home"));
		System.setProperty("bonita.home", System.getProperty("bonita.home"));
	}
}
