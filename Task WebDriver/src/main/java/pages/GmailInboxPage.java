package pages;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static utils.ElementActions.*;

import com.codeborne.selenide.SelenideElement;

public class GmailInboxPage extends BasePage{
  public GmailInboxPage() {}

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
    return isVisible(googleWorkspaceLogo);
  }

  //Actions to click on elements
  public GmailInboxPage clickOnComposeButton() {
    click(composeButton);
    return this;
  }

  public GmailInboxPage clickOnCloseMailFrameButtonIcon() {
    click(closeMailFrameButtonIcon);
    return this;
  }

  public GmailInboxPage clickOnDraftListButton() {
    click(draftListButton);
    isVisible(draftListButtonAfterClick);
    return this;
  }

  public GmailInboxPage clickOnSentListButton() {
    click(sentListButton);
    isVisible(sentListButtonAfterClick);
    return this;
  }

  public GmailInboxPage clickOnFirstMassageOnList() {
    click(firstMessage);
    return this;
  }

  public GmailInboxPage clickOnSendButton() {
    click(sendButton);
    return this;
  }

  // Actions to fill in the fields
  public GmailInboxPage setRecipientsField(String destination) {
    setTextValue(recipientsField, destination);
    return this;
  }

  public GmailInboxPage setSubjectField(String subject) {
    setTextValue(subjectField, subject);
    return this;
  }

  public GmailInboxPage setMessageField(String message) {
    setTextValue(messageField, message);
    return this;
  }

  // Actions to get value of elements
  public String getRecipientsFieldValue() {
    return getAttributeValue(recipientsFieldFilling, "email");
  }

  public String getSubjectFieldValue() {
    return getValueOfElement(subjectField);
  }

  public String getMessageFieldText() {
    return getTextValue(messageField);
  }

  public String getRecipientInTheSentMassageText() {
    String text = getTextValue(recipientInTheSentMassage);
    return text.replaceAll("^<|>$", "");
  }

  public String getSubjectInTheSentMassageText() {
    return getTextValue(subjectInTheSentMassage);
  }

  public String getMessageInTheSentMassageText() {
    return getTextValue(messageInTheSentMassage);
  }
}
