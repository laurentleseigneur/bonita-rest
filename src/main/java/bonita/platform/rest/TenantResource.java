package bonita.platform.rest;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.engine.api.PlatformLoginAPI;
import org.bonitasoft.engine.exception.BonitaException;
import org.bonitasoft.engine.exception.BonitaHomeNotSetException;
import org.bonitasoft.engine.exception.ServerAPIException;
import org.bonitasoft.engine.exception.UnknownAPITypeException;
import org.bonitasoft.engine.platform.PlatformLoginException;
import org.bonitasoft.engine.platform.PlatformLogoutException;
import org.bonitasoft.engine.session.PlatformSession;
import org.bonitasoft.engine.session.SessionNotFoundException;

import restx.annotations.DELETE;
import restx.annotations.GET;
import restx.annotations.POST;
import restx.annotations.PUT;
import restx.annotations.RestxResource;
import restx.factory.Component;
import restx.security.PermitAll;
import bonita.platform.domain.TenantCreation;
import bonita.platform.domain.TenantUpdate;
import bonita.platform.settings.BonitaSettings;

import com.bonitasoft.engine.api.PlatformAPI;
import com.bonitasoft.engine.api.PlatformAPIAccessor;
import com.bonitasoft.engine.platform.Tenant;
import com.bonitasoft.engine.platform.TenantCreator;
import com.bonitasoft.engine.platform.TenantCriterion;
import com.bonitasoft.engine.platform.TenantUpdater;

@Component
@RestxResource
public class TenantResource {
	org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());
	private PlatformLoginAPI platformLoginAPI;
	private PlatformSession session;

	public TenantResource(BonitaSettings bonitaSettings) {
		System.setProperty("bonita.home",
				System.getProperty("bonita.home", bonitaSettings.bonitaHome()));
		logger.info("using bonita home: " + System.getProperty("bonita.home"));
	}

	@GET("/tenant")
	@PermitAll
	public List<Tenant> getAllTenants() {
		List<Tenant> tenants = new ArrayList<Tenant>();
		try {
			final PlatformAPI platformAPI = getPlatformApi();
			tenants = platformAPI.getTenants(0, Integer.MAX_VALUE,
					TenantCriterion.CREATION_ASC);
		} catch (final BonitaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			logout();
		}
		return tenants;
	}

	private PlatformAPI getPlatformApi() throws BonitaHomeNotSetException,
	ServerAPIException, UnknownAPITypeException, PlatformLoginException {
		platformLoginAPI = PlatformAPIAccessor.getPlatformLoginAPI();
		session = platformLoginAPI.login("platformAdmin", "platform");
		final PlatformAPI platformAPI = PlatformAPIAccessor
				.getPlatformAPI(session);
		return platformAPI;
	}

	private void logout() {
		try {
			platformLoginAPI.logout(session);
		} catch (final PlatformLogoutException e) {
			logger.error("PlatformLogoutException", e);

		} catch (final SessionNotFoundException e) {
			logger.error("SessionNotFoundException", e);
		}
	}

	@POST("/tenant")
	public Tenant createTenant(TenantCreation tenantData) {
		try {
			final PlatformAPI platformApi = getPlatformApi();
			final TenantCreator tenantCreator = new TenantCreator(
					tenantData.getName());
			tenantCreator.setDescription(tenantData.getDescription());
			tenantCreator.setUsername(tenantData.getUserName());
			tenantCreator.setPassword(tenantData.getPassword());
			tenantCreator.setDefaultTenant(false);

			final long createTenantId = platformApi.createTenant(tenantCreator);
			return platformApi.getTenantById(createTenantId);
		} catch (final BonitaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			logout();
		}
		return null;
	}

	@DELETE("/tenant/{id}")
	public void deleteTenant(long id) {
		try {
			final PlatformAPI platformApi = getPlatformApi();
			platformApi.deleteTenant(id);
		} catch (final BonitaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			logout();
		}
	}

	@PUT("/tenant/{id}")
	public Tenant update(long id, TenantUpdate tenantData) {
		Tenant tenant = null;
		final String action = tenantData.getAction();

		if (action.equals("update")) {
			tenant = updateTenant(id, tenantData);
		} else if (action.equals("activate")) {
			activateTenant(id);

		} else if (action.equals("deactivate")) {
			deactiveTenant(id);
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
			final PlatformAPI platformApi = getPlatformApi();
			return platformApi.getTenantById(id);
		} catch (final BonitaException e) {
			e.printStackTrace();
		} finally {
			logout();
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
			final PlatformAPI platformApi = getPlatformApi();
			return platformApi.updateTenant(id, updater);
		} catch (final BonitaException e) {
			e.printStackTrace();
		} finally {
			logout();
		}
		return null;
	}

	private void activateTenant(long id) {
		try {
			final PlatformAPI platformApi = getPlatformApi();
			platformApi.activateTenant(id);
		} catch (final BonitaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			logout();
		}
	}

	private void deactiveTenant(long id) {
		try {
			final PlatformAPI platformApi = getPlatformApi();
			platformApi.deactiveTenant(id);
		} catch (final BonitaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			logout();
		}
	}

}
