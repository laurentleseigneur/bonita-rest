package bonita.platform.rest;

import org.junit.BeforeClass;

public class AbstractTest {

	@BeforeClass
	public static void before() throws Exception {
		System.setProperty("bonita.home",
				System.getProperty("bonita.home", "/home/laurent/bonita-home"));
	}

	public AbstractTest() {
		super();
	}

}