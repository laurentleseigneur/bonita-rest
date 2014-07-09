package bonita.platform.service;

import org.bonitasoft.engine.api.PlatformLoginAPI;
import org.bonitasoft.engine.exception.BonitaException;
import org.bonitasoft.engine.session.PlatformSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import restx.factory.Component;

import com.bonitasoft.engine.api.PlatformAPIAccessor;

@Component
public class PlatformSessionService {

	private PlatformLoginAPI platformLoginAPI;
	private PlatformSession platformSession;
	private final BonitaHomeService bonitaHomeService;
	Logger logger = LoggerFactory.getLogger(getClass());

	public PlatformSessionService(BonitaHomeService bonitaHomeService) {
		this.bonitaHomeService = bonitaHomeService;
		getBonitaHomeService().initBonitaHome();

	}

	public PlatformSession getSession() {
		platformSession = null;
		try {
			platformLoginAPI = PlatformAPIAccessor.getPlatformLoginAPI();
			platformSession = platformLoginAPI.login("platformAdmin",
					"platform");
		} catch (final BonitaException e) {
			logger.error("BonitaException", e);
		}
		return platformSession;
	}

	public void logout() {
		try {
			platformLoginAPI.logout(platformSession);
		} catch (final BonitaException e) {
			logger.error("BonitaException", e);
		}
	}

	public BonitaHomeService getBonitaHomeService() {
		return bonitaHomeService;
	}

}
