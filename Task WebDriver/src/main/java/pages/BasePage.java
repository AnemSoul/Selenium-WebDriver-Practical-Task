package pages;

import org.openqa.selenium.support.PageFactory;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public abstract class BasePage {
  /**
   * Базовый метод для проверки, что страница корректно загружена.
   * Реализуем в каждом наследнике.
   */
  public abstract boolean isPageLoaded();
}
