package listeners;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.LogEvent;
import com.codeborne.selenide.logevents.LogEventListener;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HighlightingDecorator implements LogEventListener {
  private final LogEventListener wrappedListener;

  public HighlightingDecorator(LogEventListener wrappedListener) {
    this.wrappedListener = wrappedListener;
  }

  @Override
  public void beforeEvent(LogEvent event) {
    wrappedListener.beforeEvent(event);

    if (event.getElement() != null) {
      try {
        WebElement element = resolveWebElement(event.getElement());
        if (element != null) {
          highlightElement(element);
        } else {
          System.out.println("Failed to resolve element for event: " + event.getElement());
        }
      } catch (Exception e) {
        System.out.println("Error during element highlighting: " + e.getMessage());
      }
    }
  }

  @Override
  public void afterEvent(LogEvent event) {
    wrappedListener.afterEvent(event);

    if (event.getElement() != null) {
      try {
        WebElement element = resolveWebElement(event.getElement());
        if (element != null) {
          clearHighlight(element);
        }
      } catch (Exception e) {
        System.out.println("Error during element clearing: " + e.getMessage());
      }
    }
  }

  private WebElement resolveWebElement(String locator) {
    try {
      // Универсальная обработка локатора
      if (locator.startsWith("By.xpath:")) {
        String xpath = locator.replace("By.xpath: ", "").trim();
        return Selenide.$x(xpath).toWebElement();
      } else if (locator.startsWith("By.cssSelector:")) {
        String css = locator.replace("By.cssSelector: ", "").trim();
        return Selenide.$(css).toWebElement();
      } else {
        return Selenide.$(locator).toWebElement();
      }
    } catch (Exception e) {
      System.out.println("Failed to resolve locator: " + locator);
      return null;
    }
  }

  private void highlightElement(WebElement element) {
    try {
      WebDriver driver = Selenide.webdriver().object();
      JavascriptExecutor js = (JavascriptExecutor) driver;
      js.executeScript("arguments[0].setAttribute('style',"
          + " 'border: 3px solid red; background-color: yellow;');", element);
    } catch (Exception e) {
      System.out.println("Failed to highlight element: " + e.getMessage());
    }
  }

  private void clearHighlight(WebElement element) {
    try {
      WebDriver driver = Selenide.webdriver().object();
      JavascriptExecutor js = (JavascriptExecutor) driver;
      js.executeScript("arguments[0].setAttribute('style', '');", element);
    } catch (Exception e) {
      System.out.println("Failed to clear highlight: " + e.getMessage());
    }
  }
}