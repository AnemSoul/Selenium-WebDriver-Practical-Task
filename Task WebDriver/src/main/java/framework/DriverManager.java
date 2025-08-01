package framework;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeOptions;
import listeners.CustomSelenideListener;

public class DriverManager {
  private DriverManager() {
    Configuration.startMaximized = true;
    Configuration.timeout = 10000;
    Configuration.pageLoadTimeout = 30000;
    SelenideLogger.addListener("CustomLogger", new CustomSelenideListener());
    setDriver("chrome");
  }

  private static DriverManager instance;

  public static void getInstance() {
    if (instance == null) {
      instance = new DriverManager();
    }
    String browser = System.getProperty("browser", "chrome");
    instance.setDriver(browser);
  }

  public void setDriver(String browser) {
    switch (browser.toLowerCase()) {
      case "chrome":
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        Configuration.browser = "chrome";
        Configuration.browserCapabilities = chromeOptions;
        break;
      case "firefox":
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        Configuration.browser = "firefox";
        Configuration.browserCapabilities = firefoxOptions;
        break;
      case "edge":
        WebDriverManager.edgedriver().setup();
        EdgeOptions edgeOptions = new EdgeOptions();
        Configuration.browser = "edge";
        Configuration.browserCapabilities = edgeOptions;
        break;
      default:
        throw new IllegalArgumentException("Unsupported browser: " + browser);
    }
  }
}