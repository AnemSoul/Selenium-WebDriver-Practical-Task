package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import utils.Waiters;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class GmailHomePage extends BasePage {
  public GmailHomePage() {}

  private static final long WAIT_SHORT = 3;
  private static final long WAIT_MEDIUM = 5;

  private static final String PAGE_URL = "https://workspace.google.com/intl/en/gmail/";

  private final SelenideElement googleWorkspaceLogo =
      $x("//img[contains(@alt, 'Google Gmail')]");

  private final SelenideElement signInButton =
        $x("(//span[text()='Sign in'])[2]");

  @Override
  public boolean isPageLoaded() {
    Waiters.waitForVisibility(googleWorkspaceLogo, WAIT_MEDIUM);
    return googleWorkspaceLogo.isDisplayed();
  }

  public GmailHomePage openPage() {
    open(PAGE_URL);
    Selenide.sleep(WAIT_MEDIUM);
    return new GmailHomePage();
  }

  public SigInPage clickSignInButton() {
    Waiters.waitForClickable(signInButton, WAIT_SHORT);
    signInButton.click();
    Selenide.sleep(WAIT_MEDIUM);
    return new SigInPage();
  }
}
