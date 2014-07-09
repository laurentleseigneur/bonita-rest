package bonita.platform.rest;

import bonita.platform.service.BonitaHomeService;
import bonita.platform.service.PlatformService;
import bonita.platform.service.PlatformSessionService;
import bonita.platform.settings.BonitaSettings;

public class AbstractTest {

	public AbstractTest() {
		super();
	}

	protected PlatformService getPlatformService() {
		final BonitaHomeService bonitaHomeService = new BonitaHomeService(
				getSettings());
		return new PlatformService(
				new PlatformSessionService(bonitaHomeService));
	}

	private BonitaSettings getSettings() {
		final BonitaSettings bonitaSettings = new BonitaSettings() {

			@Override
			public String bonitaHome() {
				return "/home/laurent/bonita-home/";
			}
		};
		return bonitaSettings;
	}

	protected BonitaHomeService getBonitaHomeService() {
		final BonitaHomeService bonitaHomeService = new BonitaHomeService(
				getSettings());
		return bonitaHomeService;
	}

}