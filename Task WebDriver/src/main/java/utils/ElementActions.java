package utils;

import com.codeborne.selenide.SelenideElement;

public class ElementActions {


  public static void click(SelenideElement element) {
    Waiters.waitForClickableMedium(element);
    element.click();
  }


  public static void setTextValue(SelenideElement element, String value) {
    Waiters.waitForVisibilityMedium(element);
    element.setValue(value);
  }

  public static String getTextValue(SelenideElement element) {
    Waiters.waitForVisibilityMedium(element);
    return element.getText();
  }

  public static String getValueOfElement(SelenideElement element) {
    Waiters.waitForVisibilityMedium(element);
    return element.getValue();
  }

  public static String getAttributeValue(SelenideElement element, String attribute) {
    Waiters.waitForVisibilityMedium(element);
    return element.getAttribute(attribute);
  }

  public static boolean isVisible(SelenideElement element) {
    try {
      Waiters.waitForVisibilityMedium(element);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}