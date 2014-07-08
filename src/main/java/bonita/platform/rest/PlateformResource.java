package bonita.platform.rest;

import org.bonitasoft.engine.api.PlatformLoginAPI;
import org.bonitasoft.engine.exception.BonitaException;
import org.bonitasoft.engine.exception.BonitaHomeNotSetException;
import org.bonitasoft.engine.exception.ServerAPIException;
import org.bonitasoft.engine.exception.UnknownAPITypeException;
import org.bonitasoft.engine.platform.PlatformLogoutException;
import org.bonitasoft.engine.platform.PlatformNotFoundException;
import org.bonitasoft.engine.platform.impl.PlatformImpl;
import org.bonitasoft.engine.session.PlatformSession;
import org.bonitasoft.engine.session.SessionNotFoundException;
import org.junit.BeforeClass;

import restx.annotations.GET;
import restx.annotations.RestxResource;
import restx.factory.Component;
import restx.security.PermitAll;

import com.bonitasoft.engine.api.PlatformAPI;
import com.bonitasoft.engine.api.PlatformAPIAccessor;

@Component
@RestxResource
public class PlateformResource {
	org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());
	private PlatformLoginAPI platformLoginAPI;
	private PlatformSession session;

	
	
	@GET("/platform")
	@PermitAll
	public PlatformImpl getPlatform() {
		System.setProperty("bonita.home", "/home/laurent/bonita-home/");
		PlatformImpl platform = null;
		try {
			platformLoginAPI = PlatformAPIAccessor.getPlatformLoginAPI();
			session = platformLoginAPI.login("platformAdmin", "platform");
			PlatformAPI platformAPI = PlatformAPIAccessor
					.getPlatformAPI(session);

			platform = (PlatformImpl) platformAPI.getPlatform();

		} catch (PlatformNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BonitaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				platformLoginAPI.logout(session);
			} catch (PlatformLogoutException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SessionNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return platform;
	}
}
