package pages;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.SelenideElement;
import utils.Waiters;

public class GmailInboxPage extends BasePage{
  public GmailInboxPage() {}

  private static final long WAIT_SHORT = 3;
  private static final long WAIT_MEDIUM = 5;

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
  private final SelenideElement sentListButton =
      $x("//*[contains(@aria-label, 'Sent')]");
  private final SelenideElement sentListButtonAfterClick =
      $x("//span/*[@tabindex='0'][text()='Sent']");
  private final SelenideElement sendButton =
      $x("//div[@role='button'][text()='Send']");

  private final SelenideElement recipientsField =
      $x("//*[@aria-haspopup='listbox']");
  private final SelenideElement recipientsFieldFilling =
      $x("//form[@enctype='multipart/form-data']//span[@email]");
  private final SelenideElement subjectField =
      $x("//*[@placeholder='Subject']");
  private final SelenideElement messageField =
      $x("(//*[@aria-label='Message Body'])[2]");

  private final SelenideElement firstMessage =
      $x("//div[@role='main']//tbody/tr[@role='row']");

  private final SelenideElement recipientInTheSentMassage =
      $("span > span.go");
  private final SelenideElement subjectInTheSentMassage =
      $("div.ha > h2");
  private final SelenideElement messageInTheSentMassage =
      $x("//*[@dir='ltr'][text()]");

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

  public GmailInboxPage clickOnSentListButton() {
    Waiters.waitForClickable(sentListButton, WAIT_SHORT);
    sentListButton.click();
    Waiters.waitForVisibility(sentListButtonAfterClick, WAIT_SHORT);
    return this;
  }

  public GmailInboxPage clickOnFirstMassageOnList() {
    Waiters.waitForClickable(firstMessage, WAIT_SHORT);
    firstMessage.click();
    return this;
  }

  public GmailInboxPage clickOnSendButton() {
    Waiters.waitForClickable(sendButton, WAIT_SHORT);
    sendButton.click();
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

  public String getRecipientInTheSentMassageText() {
    Waiters.waitForVisibility(recipientInTheSentMassage, WAIT_SHORT);
    String text = recipientInTheSentMassage.getText();
    return text.replaceAll("^<|>$", "");
  }

  public String getSubjectInTheSentMassageText() {
    Waiters.waitForVisibility(subjectInTheSentMassage, WAIT_SHORT);
    return subjectInTheSentMassage.getText();
  }

  public String getMessageInTheSentMassageText() {
    Waiters.waitForVisibility(messageInTheSentMassage, WAIT_SHORT);
    return messageInTheSentMassage.getText();
  }
}
