package listeners;

import java.nio.file.Path;
import java.nio.file.Paths;

public class AllureConfig {

  public static void configureResultsDirectory() {
    Path allureResultsPath = Paths.get("target", "allure-results");
    System.setProperty("allure.results.directory", allureResultsPath.toAbsolutePath().toString());
  }
}