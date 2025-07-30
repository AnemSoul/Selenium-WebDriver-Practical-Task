package tests;

import static org.testng.Assert.assertTrue;
import static utils.SigInManager.sigInUsSimpleUser;

import org.testng.annotations.Test;
import pages.GmailInboxPage;

public class GmailSigInTest {

  private GmailInboxPage gmailInboxPage;

  @Test
  public void testGmailSignIn() {
    gmailInboxPage = sigInUsSimpleUser();
    assertTrue(gmailInboxPage.isPageLoaded(),
        "Gmail Inbox page is not loaded correctly");
  }
}
