package tests.smoke;

import static org.testng.Assert.assertTrue;
import static utils.SigInManager.sigInAsSimpleUser;

import org.testng.annotations.Test;
import pages.GmailInboxPage;
import tests.BaseTest;

public class GmailSigInTest extends BaseTest {

  @Test
  public void testGmailSignIn() {
    GmailInboxPage gmailInboxPage = sigInAsSimpleUser();
    assertTrue(gmailInboxPage.isPageLoaded(),
        "Gmail Inbox page is not loaded correctly");
  }
}
