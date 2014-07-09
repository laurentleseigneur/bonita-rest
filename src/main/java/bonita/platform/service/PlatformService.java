package bonita.platform.service;

import org.bonitasoft.engine.exception.BonitaHomeNotSetException;
import org.bonitasoft.engine.exception.ServerAPIException;
import org.bonitasoft.engine.exception.UnknownAPITypeException;
import org.bonitasoft.engine.session.PlatformSession;

import restx.factory.Component;

import com.bonitasoft.engine.api.PlatformAPI;
import com.bonitasoft.engine.api.PlatformAPIAccessor;

@Component
public class PlatformService {

	private final PlatformSessionService platformSessionService;

	public PlatformService(PlatformSessionService platformSessionService) {
		this.platformSessionService = platformSessionService;

	}

	public PlatformAPI getPlatformAPI() {
		try {
			platformSessionService.getBonitaHomeService().initBonitaHome();
			final PlatformSession session = platformSessionService.getSession();
			return PlatformAPIAccessor.getPlatformAPI(session);

		} catch (final BonitaHomeNotSetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (final ServerAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (final UnknownAPITypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
