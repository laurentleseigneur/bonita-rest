package bonita.platform.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import restx.factory.Component;

@Component
public class BonitaHomeService {
	Logger logger = LoggerFactory.getLogger(getClass());

	public void initBonitaHome() {

		logger.info("BonitaHomeService is using with bonita.home value:"
				+ System.getProperty("bonita.home"));
		System.setProperty(
				"bonita.home",
				System.getProperty("bonita.home",
						"/home/laurent/tomcat/BonitaBPMSubscription-6.3.1-Tomcat-6.0.37/bonita/"));
	}
}
