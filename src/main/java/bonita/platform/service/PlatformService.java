package bonita.platform.service;

import org.bonitasoft.engine.exception.BonitaException;
import org.bonitasoft.engine.session.PlatformSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import restx.factory.Component;

import com.bonitasoft.engine.api.PlatformAPI;
import com.bonitasoft.engine.api.PlatformAPIAccessor;

@Component
public class PlatformService {

	private final PlatformSessionService platformSessionService;
	Logger logger = LoggerFactory.getLogger(getClass());

	public PlatformService(PlatformSessionService platformSessionService) {
		this.platformSessionService = platformSessionService;

	}

	public PlatformAPI getPlatformAPI() {
		try {
			platformSessionService.getBonitaHomeService().initBonitaHome();
			final PlatformSession session = platformSessionService.getSession();
			return PlatformAPIAccessor.getPlatformAPI(session);

		} catch (final BonitaException e) {
			logger.error("BonitaException", e);
		}
		return null;
	}
}
