package pages;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import utils.Waiters;

public class SigInPage extends BasePage{
    public SigInPage() {}

  private static final long WAIT_SHORT = 3;
  private static final long WAIT_MEDIUM = 5;
  private static final long WAIT_LONG = 10;

  private final SelenideElement googleWorkspaceLogo =
      $("c-wiz > div > svg");

  private final SelenideElement emailInputField =
      $x("//input[@name='identifier']");
  private final SelenideElement passwordInputField =
      $x("//input[@name='Passwd']");

  private final SelenideElement emailNextButton =
      $("#identifierNext > div > button");
  private final SelenideElement passwordNextButton =
      $("#passwordNext > div > button");

  @Override
  public boolean isPageLoaded() {
    Waiters.waitForVisibility(googleWorkspaceLogo, WAIT_MEDIUM);
    return googleWorkspaceLogo.isDisplayed();
  }

  //Actions to fill in the fields
  public SigInPage setEmailInputField(String email) {
    Waiters.waitForVisibility(emailInputField, WAIT_SHORT);
    emailInputField.setValue(email);
    return this;
  }

  public SigInPage setPasswordInputField(String password) {
    Waiters.waitForVisibility(passwordInputField, WAIT_LONG);
    passwordInputField.setValue(password);
    return this;
  }

  //Actions to click on elements
  public SigInPage clickOnNextButton() {
    Waiters.waitForClickable(emailNextButton, WAIT_SHORT);
    emailNextButton.click();
    return this;
  }

  public GmailInboxPage clickOnNextButtonAndGoToInbox() {
    Waiters.waitForClickable(passwordNextButton, WAIT_SHORT);
    passwordNextButton.click();
    Selenide.sleep(WAIT_MEDIUM);
    return new GmailInboxPage();
  }
}
