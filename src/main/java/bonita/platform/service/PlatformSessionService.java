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
	private BonitaHomeService bonitaHomeService;

	public PlatformSessionService(BonitaHomeService bonitaHomeService) {
		this.bonitaHomeService = bonitaHomeService;
	}

	public PlatformSession getSession() {
		platformSession = null;

		try {
			platformLoginAPI = PlatformAPIAccessor.getPlatformLoginAPI();
			platformSession = platformLoginAPI.login("platformAdmin",
					"platform");
		} catch (BonitaHomeNotSetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServerAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownAPITypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PlatformLoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return platformSession;
	}

	public void logout() {
		try {
			platformLoginAPI.logout(platformSession);
		} catch (PlatformLogoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SessionNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
