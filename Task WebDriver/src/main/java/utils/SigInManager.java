package utils;

import static framework.CredentialsManager.get;

import pages.GmailHomePage;
import pages.GmailInboxPage;

public class SigInManager {
  private static final GmailHomePage gmailHomePage = new GmailHomePage();

  public static GmailInboxPage sigInAsSimpleUser() {
    return gmailHomePage
        .openPage()
        .clickSignInButton()
        .setEmailInputField(get("test.user_email"))
        .clickOnNextButton()
        .setPasswordInputField(get("test.user_password"))
        .clickOnNextButtonAndGoToInbox();
  }
}
