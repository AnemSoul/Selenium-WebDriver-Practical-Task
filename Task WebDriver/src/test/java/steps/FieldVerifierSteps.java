package steps;

import io.cucumber.java.en.Then;
import pages.GmailInboxPage;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class FieldVerifierSteps {
  private final GmailInboxPage gmailInboxPage = new GmailInboxPage();
  private final Map<String, Supplier<String>> fieldValueGetters = new HashMap<>();

  private void initializeFieldValueGetters() {
    fieldValueGetters.put("Recipients", gmailInboxPage::getRecipientsFieldValue);
    fieldValueGetters.put("Subject", gmailInboxPage::getSubjectFieldValue);
    fieldValueGetters.put("Message", gmailInboxPage::getMessageFieldText);
    fieldValueGetters.put("Recipient in the Sent Message",
        gmailInboxPage::getRecipientInTheSentMassageText);
    fieldValueGetters.put("Subject in the Sent Message",
        gmailInboxPage::getSubjectInTheSentMassageText);
    fieldValueGetters.put("Message in the Sent Message",
        gmailInboxPage::getMessageInTheSentMassageText);
  }

  @Then("I should see the {string} field with text {string}")
  public void verifyFieldWithText(String fieldName, String expectedText) {
    if (fieldValueGetters.isEmpty()) {
      initializeFieldValueGetters();
    }
    Supplier<String> valueGetter = fieldValueGetters.get(fieldName);
    if (valueGetter != null) {
      String actualText = valueGetter.get();
      assert actualText.equals(expectedText)
          : fieldName + " field text does not match! Expected: " +
          expectedText + ", Actual: " + actualText;
    } else {
      String availableFields = String.join(", ", fieldValueGetters.keySet());
      throw new IllegalArgumentException(
          "Unknown field: \"" + fieldName + "\". Available fields are: [" + availableFields + "]"
      );
    }
  }
}