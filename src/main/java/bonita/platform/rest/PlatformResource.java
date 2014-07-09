package bonita.platform.rest;

import org.bonitasoft.engine.exception.BonitaException;
import org.bonitasoft.engine.platform.Platform;

import restx.annotations.GET;
import restx.annotations.RestxResource;
import restx.factory.Component;
import restx.security.PermitAll;
import bonita.platform.service.PlatformService;

@Component
@RestxResource
public class PlatformResource {
	org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());
	private PlatformService platformService;

	public PlatformResource(PlatformService platformService) {
		this.platformService = platformService;
	}

	@GET("/platform")
	@PermitAll
	public Platform getPlatform() {
		Platform platform = null;
		try {
			platform = platformService.getPlatformAPI().getPlatform();
		} catch (final BonitaException e) {
			logger.error("failed to get platform! ", e);
		}
		return platform;
	}

}
