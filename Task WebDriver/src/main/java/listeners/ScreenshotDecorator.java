package listeners;

import com.codeborne.selenide.Screenshots;
import com.codeborne.selenide.logevents.LogEvent;
import com.codeborne.selenide.logevents.LogEventListener;
import java.util.Objects;
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
      takeScreenshot(event);
    }
  }

  private void takeScreenshot(LogEvent event) {
    try {
      Path screenshotPath = Objects.requireNonNull(Screenshots.takeScreenShotAsFile()).toPath();
      logger.error("Screenshot saved for failed event [{}]: {}",
          event.getSubject(), screenshotPath);
    } catch (Exception e) {
      logger.error("Failed to capture screenshot for event [{}]: {}",
          event.getSubject(), e.getMessage());
    }
  }
}