package listeners;

import static listeners.decorations.ScreenshotUtils.takeScreenshotAndAttach;

import com.codeborne.selenide.logevents.LogEvent;
import com.codeborne.selenide.logevents.LogEventListener;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CustomSelenideListener implements LogEventListener {
  private static final Logger logger = LogManager.getLogger(CustomSelenideListener.class);

  @Override
  public void beforeEvent(LogEvent event) {
    logger.info("ACTION STARTED: [{}] - {}", event.getSubject(), event.getElement());
    Allure.step("Starting action: " + event.getSubject() + "with element: " + event.getElement());
  }

  @Override
  public void afterEvent(LogEvent event) {
    logger.info("ACTION FINISHED: [{} - Status: {}] - {}",
        event.getSubject(), event.getStatus(), event.getElement());

    if ("FAIL".equalsIgnoreCase(event.getStatus().name())) {
      // Скриншот при неудачном шаге
      String testName = getTestName();
      takeScreenshotAndAttach(testName, "Failed action: " + event.getSubject() + " with element: " + event.getElement());
    }

    Allure.step("Finished action: " + event.getSubject() + " (" + event.getStatus() + ")");
  }

  private String getTestName() {
    return Thread.currentThread().getStackTrace()[3].getMethodName();
  }
}