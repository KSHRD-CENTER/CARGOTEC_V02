package kh.com.kshrd;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import kh.com.kshrd.repositories.ConnectionManagement;

public class PropertiesManagement {

	public PropertiesManagement() {
		InputStream inputStream = null;
		try {
			Properties prop = new Properties();
			inputStream = getClass().getClassLoader().getResourceAsStream("application.properties");
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file application.properties not found in the classpath");
			}
			ConnectionManagement.setUrl(prop.getProperty("datasource.url"));
			ConnectionManagement.setUsername(prop.getProperty("datasource.username"));
			ConnectionManagement.setPassword(prop.getProperty("datasource.password"));
			ConnectionManagement.setDriver(prop.getProperty("datasource.driver"));
			new ConnectionManagement();
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
