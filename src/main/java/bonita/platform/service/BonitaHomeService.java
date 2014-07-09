package bonita.platform.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import restx.factory.Component;
import bonita.platform.settings.BonitaSettings;

@Component
public class BonitaHomeService {
	Logger logger = LoggerFactory.getLogger(getClass());
	private final BonitaSettings bonitaSettings;

	public BonitaHomeService(BonitaSettings bonitaSettings) {
		this.bonitaSettings = bonitaSettings;

	}

	public void initBonitaHome() {
		System.setProperty("bonita.home",
				System.getProperty("bonita.home", bonitaSettings.bonitaHome()));

		logger.info("BonitaHomeService is using with bonita.home value:"
				+ System.getProperty("bonita.home"));
	}
}
