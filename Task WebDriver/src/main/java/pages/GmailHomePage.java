package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;
import static utils.ElementActions.*;

public class GmailHomePage extends BasePage {
  public GmailHomePage() {}

  private static final String PAGE_URL = "https://workspace.google.com/intl/en/gmail/";

  private final SelenideElement googleWorkspaceLogo =
      $x("//img[contains(@alt, 'Google Gmail')]");

  private final SelenideElement signInButton =
        $x("(//span[text()='Sign in'])[2]");

  @Override
  public boolean isPageLoaded() {
    return isVisible(googleWorkspaceLogo);
  }

  public GmailHomePage openPage() {
    open(PAGE_URL);
    isPageLoaded();
    return new GmailHomePage();
  }

  public SigInPage clickSignInButton() {
    click(signInButton);
    return new SigInPage();
  }
}
