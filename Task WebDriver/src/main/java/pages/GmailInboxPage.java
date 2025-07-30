package pages;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.SelenideElement;
import utils.Waiters;

public class GmailInboxPage extends BasePage{
  public GmailInboxPage() {}

  private static final long WAIT_SHORT = 3;
  private static final long WAIT_MEDIUM = 5;
  private static final long WAIT_LONG = 10;

  private final SelenideElement googleWorkspaceLogo =
      $("div.gb_Dc > div > a > img");
  private final SelenideElement composeButton =
      $("div.T-I.T-I-KE.L3");
  private final SelenideElement closeMailFrameButtonIcon =
      $x("//*[@alt='Close']");
  private final SelenideElement draftListButton =
      $x("//*[contains(@aria-label, 'Drafts')]");
  private final SelenideElement draftListButtonAfterClick =
      $x("//span/*[@tabindex='0'][text()='Drafts']");

  private final SelenideElement recipientsField =
      $x("//*[@aria-haspopup='listbox']");
  private final SelenideElement recipientsFieldFilling =
      $x("//form[@enctype='multipart/form-data']//span[@email]");
  private final SelenideElement subjectField =
      $x("//*[@placeholder='Subject']");
  private final SelenideElement messageField =
      $x("(//*[@aria-label='Message Body'])[2]");

  private final SelenideElement firstDraft =
      $x("//div[@role='main']//tbody/tr[@role='row']");

  @Override
  public boolean isPageLoaded() {
    Waiters.waitForVisibility(googleWorkspaceLogo, WAIT_MEDIUM);
    return googleWorkspaceLogo.isDisplayed();
  }

  //Actions to click on elements
  public GmailInboxPage clickOnComposeButton() {
    Waiters.waitForClickable(composeButton, WAIT_SHORT);
    composeButton.click();
    return this;
  }

  public GmailInboxPage clickOnCloseMailFrameButtonIcon() {
    Waiters.waitForClickable(closeMailFrameButtonIcon, WAIT_SHORT);
    closeMailFrameButtonIcon.click();
    return this;
  }

  public GmailInboxPage clickOnDraftListButton() {
    Waiters.waitForClickable(draftListButton, WAIT_SHORT);
    draftListButton.click();
    Waiters.waitForVisibility(draftListButtonAfterClick, WAIT_SHORT);
    return this;
  }

  public GmailInboxPage clickOnFirstDraft() {
    Waiters.waitForClickable(firstDraft, WAIT_SHORT);
    firstDraft.click();
    return this;
  }

  // Actions to fill in the fields
  public GmailInboxPage setRecipientsField(String destination) {
    Waiters.waitForVisibility(recipientsField, WAIT_SHORT);
    recipientsField.setValue(destination);
    return this;
  }

  public GmailInboxPage setSubjectField(String subject) {
    Waiters.waitForVisibility(subjectField, WAIT_SHORT);
    subjectField.setValue(subject);
    return this;
  }

  public GmailInboxPage setMessageField(String message) {
    Waiters.waitForVisibility(messageField, WAIT_SHORT);
    messageField.setValue(message);
    return this;
  }

  // Actions to get value of elements
  public String getRecipientsFieldValue() {
    Waiters.waitForVisibility(recipientsFieldFilling, WAIT_SHORT);
    return recipientsFieldFilling.getAttribute("email");
  }

  public String getSubjectFieldValue() {
    Waiters.waitForVisibility(subjectField, WAIT_SHORT);
    return subjectField.getValue();
  }

  public String getMessageFieldText() {
    Waiters.waitForVisibility(messageField, WAIT_SHORT);
    return messageField.getText();
  }
}
