package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CredentialsManager {
  private static final Properties properties = new Properties();

  static {
    try (InputStream input = CredentialsManager
        .class
        .getClassLoader()
        .getResourceAsStream("credentials.properties")) {
      if (input == null) {
        throw new RuntimeException("Unable to load credentials.properties");
      }
      properties.load(input);
    } catch (IOException e) {
      throw new RuntimeException("Error loading credentials file", e);
    }
  }

  public static String get(String key) {
    return properties.getProperty(key);
  }
}