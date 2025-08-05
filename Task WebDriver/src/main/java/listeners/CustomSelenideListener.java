package listeners;

import com.codeborne.selenide.logevents.LogEvent;
import com.codeborne.selenide.logevents.LogEventListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CustomSelenideListener implements LogEventListener {
  private static final Logger logger = LogManager.getLogger(CustomSelenideListener.class);

  @Override
  public void beforeEvent(LogEvent event) {
    logger.info("ACTION STARTED: [{}] - {}",
        event.getSubject(), event.getElement());
  }

  @Override
  public void afterEvent(LogEvent event) {
    logger.info("ACTION FINISHED: [{} - Status: {}] - {}",
        event.getSubject(), event.getStatus(), event.getElement());
  }
}