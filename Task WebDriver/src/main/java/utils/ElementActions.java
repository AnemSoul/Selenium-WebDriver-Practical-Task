package utils;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

public class ElementActions {

  @Step("Click on element: {element}")
  public static void click(SelenideElement element) {
    Waiters.waitForClickableMedium(element);
    element.click();
  }

  @Step("Set text '{value}' for element: {element}")
  public static void setTextValue(SelenideElement element, String value) {
    Waiters.waitForVisibilityMedium(element);
    element.setValue(value);
  }

  @Step("Get text value of element: {element}")
  public static String getTextValue(SelenideElement element) {
    Waiters.waitForVisibilityMedium(element);
    return element.getText();
  }

  @Step("Get attribute '{attribute}' value from element: {element}")
  public static String getValueOfElement(SelenideElement element) {
    Waiters.waitForVisibilityMedium(element);
    return element.getValue();
  }

  @Step("Get attribute '{attribute}' value from element: {element} with attribute '{attribute}'")
  public static String getAttributeValue(SelenideElement element, String attribute) {
    Waiters.waitForVisibilityMedium(element);
    return element.getAttribute(attribute);
  }

  @Step("Check if element is visible: {element}")
  public static boolean isVisible(SelenideElement element) {
    try {
      Waiters.waitForVisibilityMedium(element);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}