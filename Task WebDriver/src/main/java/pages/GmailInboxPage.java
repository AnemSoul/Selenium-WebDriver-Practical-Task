package pages;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import utils.Waiters;

public class GmailInboxPage extends BasePage{
  public GmailInboxPage() {}

  private static final long WAIT_SHORT = 3;
  private static final long WAIT_MEDIUM = 5;
  private static final long WAIT_LONG = 10;

  private final SelenideElement googleWorkspaceLogo =
      $("div.gb_Dc > div > a > img");

  @Override
  public boolean isPageLoaded() {
    Waiters.waitForVisibility(googleWorkspaceLogo, WAIT_MEDIUM);
    return googleWorkspaceLogo.isDisplayed();
  }

}
