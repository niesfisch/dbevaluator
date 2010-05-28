package de.marcelsauer.dbevaluator.mongo;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author msauer
 * 
 */
public class Config {

	private Properties properties = new Properties();

	public Config(InputStream file) {
		try {
			this.properties.load(file);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public String getDbName() {
		return this.properties.getProperty("db.name");
	}

	public String getDbServer() {
		return this.properties.getProperty("db.server");
	}

	public int getDbServerPort() {
		return Integer.valueOf(this.properties.getProperty("db.server.port"));
	}
}
