package bonita.platform.rest;

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.engine.platform.Platform;
import org.junit.Test;

import bonita.platform.service.BonitaHomeService;
import bonita.platform.service.PlatformService;
import bonita.platform.service.PlatformSessionService;

public class PlatformResourceTest extends AbstractTest {
	@Test
	public void testGetPlatform() throws Exception {

		final BonitaHomeService bonitaHomeService = getBonitaHomeService();
		final PlatformSessionService platformSessionService = new PlatformSessionService(
				bonitaHomeService);
		final PlatformService platformService = new PlatformService(
				platformSessionService);
		// given
		final PlatformResource plateformResource = new PlatformResource(
				platformService);

		// when
		final Platform plateform = plateformResource.getPlatform();

		// then
		assertThat(plateform).isNotNull();
		assertThat(plateform.getCreatedBy()).isEqualTo("platformAdmin");
		assertThat(plateform.getVersion()).isEqualTo("6.3.1");
	}
}
