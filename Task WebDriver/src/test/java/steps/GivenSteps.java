package steps;

import static utils.SigInManager.sigInAsSimpleUser;
import static utils.SigInManager.sigInAsUserWithCredentials;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import pages.GmailHomePage;
import pages.GmailInboxPage;
import pages.SigInPage;

public class GivenSteps{

  private SigInPage sigInPage;
  private GmailInboxPage gmailInboxPage;

  @Given("I navigate to the Gmail home page")
  public void navigateToGmailHomePage() {
    GmailHomePage gmailHomePage = new GmailHomePage();
    gmailHomePage.openPage();
  }

  @And("I log in as a simple user")
  public void iLogInAsASimpleUser() {
    gmailInboxPage = sigInAsUserWithCredentials(
        "testuser001gaa2@gmail.com", "Test@1234");
  }
}
