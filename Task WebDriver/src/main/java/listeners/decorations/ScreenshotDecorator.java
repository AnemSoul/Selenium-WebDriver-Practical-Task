package listeners.decorations;

import com.codeborne.selenide.logevents.LogEvent;
import com.codeborne.selenide.logevents.LogEventListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;

public class ScreenshotDecorator implements LogEventListener {
  private static final Logger logger = LogManager.getLogger(ScreenshotDecorator.class);
  private final LogEventListener wrappedListener;

  public ScreenshotDecorator(LogEventListener wrappedListener) {
    this.wrappedListener = wrappedListener;
  }

  @Override
  public void beforeEvent(LogEvent event) {
    wrappedListener.beforeEvent(event);
  }

  @Override
  public void afterEvent(LogEvent event) {
    wrappedListener.afterEvent(event);

    if ("FAIL".equalsIgnoreCase(String.valueOf(event.getStatus()))) {
      String testClassName = getTestClassName();
      Path savedScreenshot = ScreenshotUtils.takeScreenshot(testClassName);
      if (savedScreenshot != null) {
        logger.error("Screenshot saved for failed event [{}]: {}",
            testClassName, savedScreenshot);
      }
    }
  }

  private String getTestClassName() {
    try {
      StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
      for (StackTraceElement element : stackTrace) {
        if (element.getClassName().startsWith("tests.")) {
          return element
              .getClassName()
              .substring(element.getClassName().lastIndexOf('.') + 1);
        }
      }
    } catch (Exception e) {
      logger.error("Failed to detect test class name from stack trace: {}", e.getMessage());
    }
    return "UnknownTestClass";
  }
}