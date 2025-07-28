package framework;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeOptions;

public class DriverManager {
  private static DriverManager instance;

  // Приватный конструктор для реализации Singleton
  private DriverManager() {
    // Устанавливаем общие конфигурации
    Configuration.startMaximized = true; // Браузер будет запускаться в полноэкранном режиме
    Configuration.timeout = 10000; // Тайм-аут ожидания элементов (в миллисекундах)
    Configuration.pageLoadTimeout = 30000; // Тайм-аут загрузки страницы
    setDriver("chrome"); // Chrome как дефолтный браузер
  }

  public static DriverManager getInstance() {
    if (instance == null) {
      instance = new DriverManager();
    }
    return instance;
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