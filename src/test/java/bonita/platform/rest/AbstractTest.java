package bonita.platform.rest;

import org.junit.BeforeClass;

import bonita.platform.settings.BonitaSettings;

public class AbstractTest {

	@BeforeClass
	public static void before() throws Exception {
		System.setProperty("bonita.home",
				System.getProperty("bonita.home", "/home/laurent/bonita-home"));
	}

	protected final BonitaSettings settings = new BonitaSettings() {
	
			@Override
			public String bonitaHome() {
				return "/home/laurent/bonita-home";
			}
		};

	public AbstractTest() {
		super();
	}

}