package steps;

import io.cucumber.java.en.*;
import pages.GmailInboxPage;
import pages.SigInPage;

import java.util.HashMap;
import java.util.Map;

public class SetterSteps {
  private SigInPage sigInPage;
  private GmailInboxPage gmailInboxPage;
  private final Map<String, java.util.function.Consumer<String>> textSetters = new HashMap<>();

  private void initializeTextSetters() {
    textSetters.put("email", value -> sigInPage.setEmailInputField(value));
    textSetters.put("password", value -> sigInPage.setPasswordInputField(value));
    textSetters.put("recipient", value -> gmailInboxPage.setRecipientsField(value));
    textSetters.put("subject", value -> gmailInboxPage.setSubjectField(value));
    textSetters.put("message", value -> gmailInboxPage.setMessageField(value));
  }

  @When("I set text in the {string} field to {string}")
  public void setTextInField(String fieldName, String value) {
    sigInPage = sigInPage == null ? new SigInPage() : sigInPage;
    gmailInboxPage = gmailInboxPage == null ? new GmailInboxPage() : gmailInboxPage;

    if (textSetters.isEmpty()) {
      initializeTextSetters(); // Заполняем карту
    }

    java.util.function.Consumer<String> action = textSetters.get(fieldName.toLowerCase());
    if (action != null) {
      action.accept(value); // Передаём значение прямо в лямбду
      System.out.println("Set '" + fieldName + "' field to: " + value);
    } else {
      String availableFields = String.join(", ", textSetters.keySet());
      throw new IllegalArgumentException("Unknown field: \"" + fieldName +
          "\". Available fields are: [" + availableFields + "]");
    }
  }
}
