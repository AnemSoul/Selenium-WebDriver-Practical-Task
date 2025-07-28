package framework;

import org.openqa.selenium.support.PageFactory;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public abstract class BasePage {
  public BasePage() {
    // Инициализация всех элементов страницы через PageFactory
    PageFactory.initElements(getWebDriver(), this);
  }

  /**
   * Базовый метод для проверки, что страница корректно загружена.
   * Реализуем в каждом наследнике.
   */
  public abstract boolean isPageLoaded();
}
