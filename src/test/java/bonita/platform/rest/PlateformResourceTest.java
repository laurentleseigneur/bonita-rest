package bonita.platform.rest;


import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.engine.platform.impl.PlatformImpl;
import org.junit.Test;


public class PlateformResourceTest {
@Test
public void testGetPlatform() throws Exception {
	//given
	PlateformResource plateformResource=new PlateformResource();
	
	//when
	PlatformImpl plateform = plateformResource.getPlatform();
	
	//then
	assertThat(plateform).isNotNull();
	assertThat(plateform.getCreatedBy()).isEqualTo("platformAdmin");
	assertThat(plateform.getVersion()).isEqualTo("6.3.1"); 
}
}
