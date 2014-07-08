package bonita.platform.rest;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.engine.api.PlatformLoginAPI;
import org.bonitasoft.engine.exception.BonitaException;
import org.bonitasoft.engine.platform.PlatformLogoutException;
import org.bonitasoft.engine.session.PlatformSession;
import org.bonitasoft.engine.session.SessionNotFoundException;

import restx.annotations.GET;
import restx.annotations.RestxResource;
import restx.factory.Component;
import restx.security.PermitAll;

import com.bonitasoft.engine.api.PlatformAPI;
import com.bonitasoft.engine.api.PlatformAPIAccessor;
import com.bonitasoft.engine.platform.Tenant;
import com.bonitasoft.engine.platform.TenantCriterion;

@Component
@RestxResource
public class TenantResource {
	org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());
	private PlatformLoginAPI platformLoginAPI;
	private PlatformSession session;

	@GET("/tenant")
	@PermitAll
	public List<Tenant> getAllTenants() {
		System.setProperty("bonita.home", "/home/laurent/bonita-home/");
		List<Tenant> tenants = new ArrayList<Tenant>();
		try {
			platformLoginAPI = PlatformAPIAccessor.getPlatformLoginAPI();
			session = platformLoginAPI.login("platformAdmin", "platform");
			PlatformAPI platformAPI = PlatformAPIAccessor
					.getPlatformAPI(session);

			tenants = platformAPI.getTenants(0, Integer.MAX_VALUE,
					TenantCriterion.CREATION_ASC);

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

		return tenants;
	}
}
