package bonita.platform.rest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

import com.bonitasoft.engine.platform.Tenant;

public class TenantResourceTest {
	@Test
	public void testAllTenants() throws Exception {
		// given

		TenantResource tenantResource = new TenantResource();

		// when
		List<Tenant> allTenants = tenantResource.getAllTenants();

		// then
		assertThat(allTenants).isNotEmpty().hasSize(1);
	}
}