package bonita.platform.service;

import org.bonitasoft.engine.api.PlatformLoginAPI;
import org.bonitasoft.engine.exception.BonitaHomeNotSetException;
import org.bonitasoft.engine.exception.ServerAPIException;
import org.bonitasoft.engine.exception.UnknownAPITypeException;
import org.bonitasoft.engine.platform.PlatformLoginException;
import org.bonitasoft.engine.platform.PlatformLogoutException;
import org.bonitasoft.engine.session.PlatformSession;
import org.bonitasoft.engine.session.SessionNotFoundException;

import restx.factory.Component;

import com.bonitasoft.engine.api.PlatformAPIAccessor;

@Component
public class PlatformSessionService {

	private PlatformLoginAPI platformLoginAPI;
	private PlatformSession platformSession;
	private final BonitaHomeService bonitaHomeService;

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
		} catch (final BonitaHomeNotSetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (final ServerAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (final UnknownAPITypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (final PlatformLoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return platformSession;
	}

	public void logout() {
		try {
			platformLoginAPI.logout(platformSession);
		} catch (final PlatformLogoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (final SessionNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public BonitaHomeService getBonitaHomeService() {
		return bonitaHomeService;
	}

}
