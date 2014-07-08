package bonita.platform.settings;

import restx.config.Settings;
import restx.config.SettingsKey;

@Settings
public interface BonitaSettings {

	@SettingsKey(key = "bonita.home")
	String bonitaHome();
}
