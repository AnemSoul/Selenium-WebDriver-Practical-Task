package framework;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CredentialsManager {
  private static final Properties properties = new Properties();

  static {
    String propertiesFileName = System.getProperty("env");
    if (propertiesFileName == null || propertiesFileName.isEmpty()) {
      throw new RuntimeException(
          "System property 'env' is not set. Please provide the file name as a parameter, e.g., "
              + "'-Dproperties=credentials.dev'");
    }

    String fullPath = "config/credentials." + propertiesFileName + ".properties";

    try (InputStream input = CredentialsManager.class.getClassLoader().getResourceAsStream(fullPath)) {
      if (input == null) {
        throw new RuntimeException("Unable to load properties file: " + fullPath);
      }
      properties.load(input);
    } catch (IOException e) {
      throw new RuntimeException("Error loading properties file: " + fullPath, e);
    }
  }

  public static String get(String key) {
    return properties.getProperty(key);
  }
}