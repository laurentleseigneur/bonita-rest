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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (final SessionNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public Tenant updateTenant(long id, TenantCreation tenantData) {
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

	@PUT("/tenant/{id}")
	public void activateTenant(long id) {
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

	@PUT("/tenant/{id}")
	public void deactiveTenant(long id) {
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
