package fr.eni.dao.utils;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

import fr.eni.utils.MonLogger;


public abstract class Settings {
	private static Properties properties;
	private static Logger monLogger = MonLogger.getLogger("Settings");
	
	static{
		String configPath = "Settings.properties";
		properties = new Properties();
		// ouvre un fichier à l'intérieur du répertoire où se trouve la classe Settings
		try {
			properties.load(Settings.class.getResourceAsStream(configPath));
		} catch (IOException e) {
			monLogger.severe(e.getMessage());
		}
	}
	
	// accès à chaque paramètre du fichier de configuration	
	public static String getSetting(String property){
		return properties.getProperty(property, null);
	}
}