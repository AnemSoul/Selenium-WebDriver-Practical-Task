package utils;

import pages.GmailHomePage;
import pages.GmailInboxPage;

public class SigInManager {
  private static final GmailHomePage gmailHomePage = new GmailHomePage();

  public static GmailInboxPage sigInUsSimpleUser() {
    return gmailHomePage
        .openPage()
        .clickSignInButton()
        .setEmailInputField(CredentialsManager.get("test.user_email"))
        .clickOnNextButton()
        .setPasswordInputField(CredentialsManager.get("test.user_password"))
        .clickOnNextButtonAndGoToInbox();
  }
}
