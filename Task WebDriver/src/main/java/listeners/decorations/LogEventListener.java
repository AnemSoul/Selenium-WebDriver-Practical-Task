package listeners.decorations;

import com.codeborne.selenide.logevents.LogEvent;

public interface LogEventListener {

  void beforeEvent(LogEvent event);

  void afterEvent(LogEvent event);
}
