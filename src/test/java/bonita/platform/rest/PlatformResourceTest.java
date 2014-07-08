package bonita.platform.rest;

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.engine.platform.Platform;
import org.junit.Test;

import bonita.platform.settings.BonitaSettings;

public class PlatformResourceTest extends AbstractTest {
	@Test
	public void testGetPlatform() throws Exception {

		final BonitaSettings settings = new BonitaSettings() {

			@Override
			public String bonitaHome() {
				return "/home/laurent/bonita-home";
			}
		};
		// given
		final PlatformResource plateformResource = new PlatformResource(
				settings);

		// when
		final Platform plateform = plateformResource.getPlatform();

		// then
		assertThat(plateform).isNotNull();
		assertThat(plateform.getCreatedBy()).isEqualTo("platformAdmin");
		assertThat(plateform.getVersion()).isEqualTo("6.3.1");
	}
}
