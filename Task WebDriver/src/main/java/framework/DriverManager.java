package framework;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import framework.configure.ChromeDriverSetup;
import framework.configure.DriverSetup;
import framework.configure.EdgeDriverSetup;
import framework.configure.FirefoxDriverSetup;
import listeners.CustomSelenideListener;
import listeners.HighlightingDecorator;
import listeners.ScreenshotDecorator;

public class DriverManager {
  private static volatile DriverManager instance;

  private DriverManager() {
    Configuration.startMaximized = true;
    Configuration.timeout = 10000;
    Configuration.pageLoadTimeout = 30000;

    initializeListeners();

    String browser = System.getProperty("browser", "chrome");
    configureDriver(browser);
  }

  public static DriverManager getInstance() {
    if (instance == null) {
      synchronized (DriverManager.class) {
        if (instance == null) {
          instance = new DriverManager();
        }
      }
    }
    return instance;
  }

  private void configureDriver(String browser) {
    DriverSetup driverSetup = switch (browser.toLowerCase()) {
      case "chrome" -> new ChromeDriverSetup();
      case "firefox" -> new FirefoxDriverSetup();
      case "edge" -> new EdgeDriverSetup();
      default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
    };
    driverSetup.configure();
  }

  private void initializeListeners() {
    CustomSelenideListener baseListener = new CustomSelenideListener();

    com.codeborne.selenide.logevents.LogEventListener decoratedListener = new HighlightingDecorator(
        new ScreenshotDecorator(baseListener)
    );

    SelenideLogger.addListener("CustomListener", decoratedListener);
  }
}
