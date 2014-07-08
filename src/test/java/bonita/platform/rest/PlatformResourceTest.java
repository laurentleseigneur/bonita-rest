package bonita.platform.rest;


import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.engine.platform.Platform;
import org.junit.Test;


public class PlatformResourceTest {
@Test
public void testGetPlatform() throws Exception {
	//given
	PlatformResource plateformResource=new PlatformResource();
	
	//when
	Platform plateform = plateformResource.getPlatform();
	
	//then
	assertThat(plateform).isNotNull();
	assertThat(plateform.getCreatedBy()).isEqualTo("platformAdmin");
	assertThat(plateform.getVersion()).isEqualTo("6.3.1"); 
}
}
