package pl.sparkmedia.simpletest;

import java.io.InputStream;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestProperties {

	private static final Logger LOG = LoggerFactory.getLogger(TestProperties.class);
	private static final String DEFAULT_CONFIG_PROP_FILE = "config.properties";

	private final String currentPropFilePath;
	private final Properties properties;

	public TestProperties() {
		this(DEFAULT_CONFIG_PROP_FILE);
		LOG.debug("Using default config file name and path");
	}

	public TestProperties(String propFilePath) {
		this.currentPropFilePath = propFilePath;
		properties = new Properties();
		LOG.debug("Current properties path is {}", this.currentPropFilePath);
	}

	public void load() {
		try {
			InputStream resourceIn = getClass().getClassLoader().getResourceAsStream(this.currentPropFilePath);
			LOG.info("input stream status: {} bytes", resourceIn.available());
			properties.load(resourceIn);
		} catch (Exception e) {
			LOG.error("Error while loading propertity file {} -> {}", this.currentPropFilePath, e.getMessage());
		}

		if (!properties.isEmpty()) {
			properties.stringPropertyNames()
					.forEach(propName -> System.setProperty(propName, properties.getProperty(propName)));
		}
	}

	public String getProp(String key) {
		return properties.getProperty(key);
	}

	public void setProp(String key, String value) {
		properties.setProperty(key, value);
	}

	public Properties getProperties() {
		return properties;
	}

}
