package framework.configure;

import com.codeborne.selenide.Configuration;

public class FirefoxDriverSetup implements DriverSetup {

  @Override
  public void configure() {
    Configuration.browser = "firefox";
  }
}