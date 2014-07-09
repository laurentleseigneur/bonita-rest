package bonita.platform.rest;

import java.util.List;

import org.bonitasoft.engine.exception.BonitaException;

import restx.annotations.DELETE;
import restx.annotations.GET;
import restx.annotations.POST;
import restx.annotations.PUT;
import restx.annotations.RestxResource;
import restx.factory.Component;
import restx.security.PermitAll;
import bonita.platform.domain.TenantCreation;
import bonita.platform.domain.TenantUpdate;
import bonita.platform.service.PlatformService;

import com.bonitasoft.engine.api.PlatformAPI;
import com.bonitasoft.engine.platform.Tenant;
import com.bonitasoft.engine.platform.TenantCreator;
import com.bonitasoft.engine.platform.TenantCriterion;
import com.bonitasoft.engine.platform.TenantUpdater;

@Component
@RestxResource
@PermitAll
public class TenantResource {
	private static final String DEACTIVATE = "deactivated";

	private static final String ACTIVATE = "activated";

	private static final String UPDATE = "update";

	private static final String PAUSED = "paused";

	org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());

	private final PlatformService platformService;

	public TenantResource(PlatformService platformService) {
		this.platformService = platformService;

	}

	@GET("/tenant")
	public List<Tenant> getAllTenants() {
		return platformService.getPlatformAPI().getTenants(0,
				Integer.MAX_VALUE, TenantCriterion.CREATION_ASC);
	}

	@POST("/tenant")
	public Tenant createTenant(TenantCreation tenantData) {
		try {
			final PlatformAPI platformApi = platformService.getPlatformAPI();
			final TenantCreator tenantCreator = new TenantCreator(
					tenantData.getName());
			tenantCreator.setDescription(tenantData.getDescription());
			tenantCreator.setUsername(tenantData.getUserName());
			tenantCreator.setPassword(tenantData.getPassword());
			tenantCreator.setDefaultTenant(false);

			final long createTenantId = platformApi.createTenant(tenantCreator);
			if (tenantData.isActivated()) {
				platformApi.activateTenant(createTenantId);
			}
			return platformApi.getTenantById(createTenantId);
		} catch (final BonitaException e) {

		}
		return null;
	}

	@DELETE("/tenant/{id}")
	public void deleteTenant(long id) {
		try {
			final PlatformAPI platformApi = platformService.getPlatformAPI();
			platformApi.deleteTenant(id);
		} catch (final BonitaException e) {
			logger.error("BonitaException", e);
		}
	}

	@PUT("/tenant/{id}")
	public Tenant update(long id, TenantUpdate tenantData) {
		Tenant tenant = null;
		final String action = tenantData.getState();
		switch (action) {
		case ACTIVATE:
			activateTenant(id);
			break;
		case DEACTIVATE:
			deactiveTenant(id);
			break;
		case UPDATE:
		default:
			tenant = updateTenant(id, tenantData);
			break;
		}

		if (tenant == null) {
			tenant = getTenant(id);
		}
		return tenant;

	}

	/**
	 *
	 * @param id
	 * @return the tenant
	 */
	@GET("/tenant/{id}")
	public Tenant getTenant(long id) {
		try {
			final PlatformAPI platformApi = platformService.getPlatformAPI();
			return platformApi.getTenantById(id);
		} catch (final BonitaException e) {
			logger.error("BonitaException", e);

		}
		return null;
	}

	private Tenant updateTenant(long id, TenantUpdate tenantData) {
		final TenantUpdater updater = new TenantUpdater();
		updater.setDescription(tenantData.getDescription());
		updater.setName(tenantData.getName());
		updater.setUsername(tenantData.getUserName());
		updater.setPassword(tenantData.getPassword());
		try {
			final PlatformAPI platformApi = platformService.getPlatformAPI();
			return platformApi.updateTenant(id, updater);
		} catch (final BonitaException e) {
			logger.error("BonitaException", e);
		}
		return null;
	}

	private void activateTenant(long id) {
		try {
			final PlatformAPI platformApi = platformService.getPlatformAPI();
			platformApi.activateTenant(id);
		} catch (final BonitaException e) {
			logger.error("BonitaException", e);
		}
	}

	private void deactiveTenant(long id) {
		try {
			final PlatformAPI platformApi = platformService.getPlatformAPI();
			platformApi.deactiveTenant(id);
		} catch (final BonitaException e) {
			logger.error("BonitaException", e);
		}
	}

}
