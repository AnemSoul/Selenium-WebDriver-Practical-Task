package utils;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import java.time.Duration;

public class Waiters {

  public static void waitForVisibility(SelenideElement element, long timeout) {
    element.shouldBe(Condition.visible, Duration.ofSeconds(timeout));
  }

  public static void waitForClickable(SelenideElement element, long timeout) {
    element.shouldBe(Condition.enabled, Duration.ofSeconds(timeout));
  }
}
