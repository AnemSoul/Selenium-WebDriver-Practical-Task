package tests.smoke;

import static org.testng.Assert.assertTrue;
import static utils.SigInManager.sigInUsSimpleUser;

import org.testng.annotations.Test;
import pages.GmailInboxPage;

public class GmailSigInTest {

  @Test
  public void testGmailSignIn() {
    GmailInboxPage gmailInboxPage = sigInUsSimpleUser();
    assertTrue(gmailInboxPage.isPageLoaded(),
        "Gmail Inbox page is not loaded correctly");
  }
}
