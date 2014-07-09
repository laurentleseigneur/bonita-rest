package bonita.platform.rest;

import org.bonitasoft.engine.exception.BonitaException;
import org.bonitasoft.engine.platform.Platform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import restx.annotations.GET;
import restx.annotations.RestxResource;
import restx.factory.Component;
import restx.security.PermitAll;
import bonita.platform.service.PlatformService;

@Component
@RestxResource
@PermitAll
public class PlatformResource {
	Logger logger = LoggerFactory.getLogger(getClass());
	private final PlatformService platformService;

	public PlatformResource(PlatformService platformService) {
		this.platformService = platformService;
	}

	@GET("/platform")
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
