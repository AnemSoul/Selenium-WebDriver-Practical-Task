package framework.configure;

import com.codeborne.selenide.Configuration;

public class EdgeDriverSetup implements DriverSetup {
  @Override
  public void configure() {
    System.setProperty("webdriver.edge.driver", "C:/tools/chromedriver/msedgedriver.exe");
    Configuration.browser = "edge";
  }
}