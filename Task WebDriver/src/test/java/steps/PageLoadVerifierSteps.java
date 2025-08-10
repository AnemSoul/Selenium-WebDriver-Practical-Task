package steps;

import io.cucumber.java.en.Then;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BooleanSupplier;
import pages.GmailHomePage;
import pages.GmailInboxPage;
import pages.SigInPage;

public class PageLoadVerifierSteps {
  private GmailHomePage gmailHomePage;
  private SigInPage sigInPage;
  private GmailInboxPage gmailInboxPage;

  private final Map<String, BooleanSupplier> pageLoadVerifiers = new HashMap<>();

  private void initializePageLoadVerifiers() {
    pageLoadVerifiers.put("Gmail Inbox", () ->
        (gmailInboxPage == null ? gmailInboxPage = new GmailInboxPage() : gmailInboxPage)
            .isPageLoaded());
    pageLoadVerifiers.put("Sign In", () ->
        (sigInPage == null ? sigInPage = new SigInPage() : sigInPage)
            .isPageLoaded());
    pageLoadVerifiers.put("Home", () ->
        (gmailHomePage == null ? gmailHomePage = new GmailHomePage() : gmailHomePage)
            .isPageLoaded());
  }

  @Then("I should see the {string} page loaded")
  public void verifyPageLoaded(String pageName) {
    if (pageLoadVerifiers.isEmpty()) {
      initializePageLoadVerifiers();
    }
    BooleanSupplier verifier = pageLoadVerifiers.get(pageName);
    if (verifier != null) {
      assert verifier.getAsBoolean() : pageName + " page is not loaded correctly.";
    } else {
      String availablePages = String.join(", ", pageLoadVerifiers.keySet());
      throw new IllegalArgumentException(
          "Unknown page: \"" + pageName + "\". Available pages are: [" + availablePages + "]"
      );
    }
  }
}
