package bonita.platform.rest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.UUID;

import org.junit.Test;

import bonita.platform.domain.TenantCreation;

import com.bonitasoft.engine.platform.Tenant;

public class TenantResourceTest extends AbstractTest {
	private static final String PASSWORD = "password";
	private static final String USER_NAME = "userName";
	private static final String DESCRIPTION = "description";
	private static final String NAME = "name";

	@Test
	public void testAllTenants() throws Exception {
		final TenantResource tenantResource = new TenantResource(
				getPlatformService());

		// when
		final List<Tenant> allTenants = tenantResource.getAllTenants();

		// then
		assertThat(allTenants).isNotEmpty();
	}

	@Test
	public void testCreateTenant() throws Exception {
		// given

		// when
		final String uniqueName = NAME + UUID.randomUUID();
		final Tenant createTenant = new TenantResource(getPlatformService())
		.createTenant(TenantCreation.build(uniqueName, DESCRIPTION,
				USER_NAME, PASSWORD, true));

		// then
		assertThat(createTenant.getName()).isEqualTo(uniqueName);
		assertThat(createTenant.getDescription()).isEqualTo(DESCRIPTION);
		assertThat(createTenant.getId()).isGreaterThan(0);

	}

}