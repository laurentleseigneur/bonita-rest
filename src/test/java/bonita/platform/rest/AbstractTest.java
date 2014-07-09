package bonita.platform.rest;

import org.junit.BeforeClass;

import bonita.platform.service.BonitaHomeService;
import bonita.platform.service.PlatformService;
import bonita.platform.service.PlatformSessionService;

public class AbstractTest {

	@BeforeClass
	public static void before() throws Exception {
		System.setProperty("bonita.home",
				System.getProperty("bonita.home", "/home/laurent/distrib/6.3.1/BonitaBPMSubscription-6.3.1-Tomcat-6.0.37/bonita/"));
	}

	public AbstractTest() {
		super();
	}

	protected PlatformService getPlatformService() {
		BonitaHomeService bonitaHomeService = new BonitaHomeService();
		return new PlatformService(new PlatformSessionService(bonitaHomeService));
	}

	protected BonitaHomeService getBonitaHomeService() {
		final BonitaHomeService bonitaHomeService = new BonitaHomeService();
		return bonitaHomeService;
	}

}