package bonita.platform.rest;

import bonita.platform.service.BonitaHomeService;
import bonita.platform.service.PlatformService;
import bonita.platform.service.PlatformSessionService;

public class AbstractTest {

	public AbstractTest() {
		super();
	}

	protected PlatformService getPlatformService() {
		final BonitaHomeService bonitaHomeService = new BonitaHomeService();
		return new PlatformService(
				new PlatformSessionService(bonitaHomeService));
	}

	protected BonitaHomeService getBonitaHomeService() {
		final BonitaHomeService bonitaHomeService = new BonitaHomeService();
		return bonitaHomeService;
	}

}