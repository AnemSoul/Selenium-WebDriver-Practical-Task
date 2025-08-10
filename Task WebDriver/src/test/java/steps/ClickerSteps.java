package steps;

import java.util.HashMap;
import java.util.Map;
import pages.GmailHomePage;
import pages.GmailInboxPage;
import pages.SigInPage;
import io.cucumber.java.en.*;

public class ClickerSteps {

  private GmailHomePage gmailHomePage;
  private SigInPage sigInPage;
  private GmailInboxPage gmailInboxPage;

  private final Map<String, Runnable> buttonActions = new HashMap<>();

  private void initializeButtonActions() {
    buttonActions.put("sign in", () ->
        sigInPage = (gmailHomePage == null ? new GmailHomePage() : gmailHomePage)
            .clickSignInButton());
    buttonActions.put("email next", () ->
        sigInPage = sigInPage.clickOnNextButton());
    buttonActions.put("password next", () ->
        gmailInboxPage = sigInPage.clickOnNextButtonAndGoToInbox());
    buttonActions.put("compose", () ->
        gmailInboxPage = gmailInboxPage.clickOnComposeButton());
    buttonActions.put("close mail frame", () ->
        gmailInboxPage = gmailInboxPage.clickOnCloseMailFrameButtonIcon());
    buttonActions.put("Draft list" , () ->
        gmailInboxPage = gmailInboxPage.clickOnDraftListButton());
    buttonActions.put("First message on the list" , () ->
        gmailInboxPage = gmailInboxPage.clickOnFirstMassageOnList());
  }

  @When("I click on the {string} button")
  public void clickOnButtonUsingMap(String buttonName) {
    if (buttonActions.isEmpty()) {
      initializeButtonActions();
    }
    Runnable action = buttonActions.get(buttonName.toLowerCase());
    if (action != null) {
      action.run();
    } else {
      String availableButtons = String.join(", ", buttonActions.keySet());
      throw new IllegalArgumentException(
          "Unknown button: \"" + buttonName +
              "\". Available buttons are: [" + availableButtons + "]"
      );
    }
  }
}
