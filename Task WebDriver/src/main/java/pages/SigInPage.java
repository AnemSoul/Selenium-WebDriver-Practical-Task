package pages;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static utils.ElementActions.*;

import com.codeborne.selenide.SelenideElement;

public class SigInPage extends BasePage{
    public SigInPage() {}


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
    return isVisible(googleWorkspaceLogo);
  }

  //Actions to fill in the fields
  public SigInPage setEmailInputField(String email) {
    setTextValue(emailInputField, email);
    return this;
  }

  public SigInPage setPasswordInputField(String password) {
    setTextValue(passwordInputField, password);
    return this;
  }

  //Actions to click on elements
  public SigInPage clickOnNextButton() {
    click(emailNextButton);
    return this;
  }

  public GmailInboxPage clickOnNextButtonAndGoToInbox() {
    click(passwordNextButton);
    return new GmailInboxPage();
  }
}
