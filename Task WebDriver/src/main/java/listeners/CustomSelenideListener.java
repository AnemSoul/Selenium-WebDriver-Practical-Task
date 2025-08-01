package listeners;

import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.LogEvent;
import com.codeborne.selenide.logevents.LogEventListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

public class CustomSelenideListener implements LogEventListener {

  private static final Logger logger = LogManager.getLogger(CustomSelenideListener.class);

  @Override
  public void beforeEvent(LogEvent event) {
    logger.info("ACTION STARTED: [{}] - {}",
        event.getSubject(), describeElement(event));
    if (event.getElement() != null) {
      try {
        WebElement element = resolveWebElement(event.getElement());
        if (element != null && isElementVisible(element)) {
          highlightElement(element);
        } else {
          logger.warn("Element is not visible or not found: [{}]",
              event.getElement());
        }
      } catch (Exception e) {
        logger.warn("Failed to highlight element: [{}]. Error: {}",
            event.getElement(), e.getMessage());
      }
    }
  }

  @Override
  public void afterEvent(LogEvent event) {
    logger.info("ACTION FINISHED: [{} - Status: {}] - {}",
        event.getSubject(), event.getStatus(), describeElement(event));
    if (event.getElement() != null) {
      try {
        WebElement element = resolveWebElement(event.getElement());
        if (element != null) {
          clearHighlight(element);
        }
      } catch (Exception e) {
        logger.warn("Failed to clear highlight: [{}]. Error: {}",
            event.getElement(), e.getMessage());
      }
    }
  }

  private void highlightElement(WebElement element) {
    WebDriver driver = WebDriverRunner.getWebDriver();
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript(
        "arguments[0].style.border='5px solid red'; arguments[0].style.backgroundColor='yellow';",
        element);
  }

  private void clearHighlight(WebElement element) {
    WebDriver driver = WebDriverRunner.getWebDriver();
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript(
        "arguments[0].style.border=''; arguments[0].style.backgroundColor='';",
        element);
  }

  private WebElement resolveWebElement(String locator) {
    WebDriver driver = WebDriverRunner.getWebDriver();

    try {
      if (locator.startsWith("By.xpath:")) {
        String xpath = locator.replaceFirst("By\\.xpath:\\s*", "");
        return Selenide.$(By.xpath(xpath)).toWebElement();
      } else if (locator.startsWith("By.cssSelector:")) {
        String css = locator.replaceFirst("By\\.cssSelector:\\s*", "");
        return Selenide.$(By.cssSelector(css)).toWebElement();
      } else if (
          locator.startsWith("#") ||
          locator.startsWith(".") ||
          locator.contains(">") ||
          locator.contains("div.")
      ) {
        return Selenide.$(By.cssSelector(locator)).toWebElement();
      } else {
        logger.warn("Unknown locator format: [{}]", locator);
      }
    } catch (Exception e) {
      logger.warn("Failed to resolve locator [{}]: {}", locator, e.getMessage());
    }
    return null;
  }

  private boolean isElementVisible(WebElement element) {
    try {
      return element.isDisplayed();
    } catch (Exception e) {
      logger.debug("Element visibility check failed: {}", e.getMessage());
      return false;
    }
  }

  private String describeElement(LogEvent event) {
    return event.getElement() != null ? event.getElement() : "N/A";
  }
}