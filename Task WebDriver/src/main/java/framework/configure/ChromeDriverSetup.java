package framework.configure;

import com.codeborne.selenide.Configuration;

public class ChromeDriverSetup implements DriverSetup {

  @Override
  public void configure() {
    Configuration.browser = "chrome";
  }
}
