package tests.regression;

import static utils.SigInManager.sigInUsSimpleUser;
import static utils.TextGenerator.generateRandomText;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.GmailInboxPage;
import tests.BaseTest;

public class GmailDraftSaveAndSendTest extends BaseTest {
  private static final String EMAIL = "testuser001gaa@gmail.com";
  private static final String SUBJECT = generateRandomText(10);
  private static final String MESSAGE = generateRandomText(50);

  private GmailInboxPage gmailInboxPage;

  @BeforeClass
  public void setUp() {
    super.setUp();
    gmailInboxPage = sigInUsSimpleUser()
        .clickOnComposeButton()
        .setRecipientsField(EMAIL)
        .setSubjectField(SUBJECT)
        .setMessageField(MESSAGE)
        .clickOnCloseMailFrameButtonIcon()
        .clickOnDraftListButton();;
  }

  @Test(priority = 1)
  public void testDraftSave() {
    gmailInboxPage.clickOnFirstMassageOnList();
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(
        gmailInboxPage.getRecipientsFieldValue(), EMAIL,
        "Recipient email does not match");
    softAssert.assertEquals(
        gmailInboxPage.getSubjectFieldValue(), SUBJECT,
        "Subject does not match");
    softAssert.assertEquals(
        gmailInboxPage.getMessageFieldText(), MESSAGE,
        "Message does not match");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void testDraftSend() {
    gmailInboxPage.clickOnSendButton().clickOnSentListButton().clickOnFirstMassageOnList();
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(
        gmailInboxPage.getRecipientInTheSentMassageText(), EMAIL,
        "Recipient email does not match");
    softAssert.assertEquals(
        gmailInboxPage.getSubjectInTheSentMassageText(), SUBJECT,
        "Subject does not match");
    softAssert.assertEquals(
        gmailInboxPage.getMessageInTheSentMassageText(), MESSAGE,
        "Message does not match");
    softAssert.assertAll();
  }
}
