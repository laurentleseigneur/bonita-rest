package bonita.platform.rest;

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.engine.platform.Platform;
import org.junit.Test;

public class PlatformResourceTest extends AbstractTest {
	@Test
	public void testGetPlatform() throws Exception {
		// given
		final PlatformResource plateformResource = new PlatformResource();

		// when
		final Platform plateform = plateformResource.getPlatform();

		// then
		assertThat(plateform).isNotNull();
		assertThat(plateform.getCreatedBy()).isEqualTo("platformAdmin");
		assertThat(plateform.getVersion()).isEqualTo("6.3.1");
	}
}
