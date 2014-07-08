package bonita.platform.rest;

import org.bonitasoft.engine.api.PlatformLoginAPI;
import org.bonitasoft.engine.exception.BonitaException;
import org.bonitasoft.engine.platform.Platform;
import org.bonitasoft.engine.platform.PlatformLogoutException;
import org.bonitasoft.engine.session.PlatformSession;
import org.bonitasoft.engine.session.SessionNotFoundException;

import restx.annotations.GET;
import restx.annotations.RestxResource;
import restx.factory.Component;
import restx.security.PermitAll;

import com.bonitasoft.engine.api.PlatformAPI;
import com.bonitasoft.engine.api.PlatformAPIAccessor;

@Component
@RestxResource
public class PlatformResource {
	org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());

	private PlatformLoginAPI platformLoginAPI;

	private PlatformSession platformSession;

	@GET("/platform")
	@PermitAll
	public Platform getPlatform() {
		Platform platform = null;
		try {
			platformLoginAPI = PlatformAPIAccessor.getPlatformLoginAPI();
			platformSession = platformLoginAPI.login("platformAdmin",
					"platform");
			final PlatformAPI platformAPI = PlatformAPIAccessor
					.getPlatformAPI(platformSession);

			platform = platformAPI.getPlatform();

		} catch (final BonitaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
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

		return platform;
	}
}
