package utils;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

public class Waiters {

  private static final long SHORT_WAIT = 3;
  private static final long MEDIUM_WAIT = 5;
  private static final long LONG_WAIT = 10;

  //visible
  public static void waitForVisibilityShort(SelenideElement element) {
    element.shouldBe(Condition.visible, Duration.ofSeconds(SHORT_WAIT));
  }

  public static void waitForVisibilityMedium(SelenideElement element) {
    element.shouldBe(Condition.visible, Duration.ofSeconds(MEDIUM_WAIT));
  }

  public static void waitForVisibilityLong(SelenideElement element) {
    element.shouldBe(Condition.visible, Duration.ofSeconds(LONG_WAIT));
  }

  //clickable
  public static void waitForClickableShort(SelenideElement element) {
    element.shouldBe(Condition.enabled, Duration.ofSeconds(SHORT_WAIT));
  }

  public static void waitForClickableMedium(SelenideElement element) {
    element.shouldBe(Condition.enabled, Duration.ofSeconds(MEDIUM_WAIT));
  }

  public static void waitForClickableLong(SelenideElement element) {
    element.shouldBe(Condition.enabled, Duration.ofSeconds(LONG_WAIT));
  }
}