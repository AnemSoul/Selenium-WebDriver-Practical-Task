package listeners;

import static listeners.decorations.ScreenshotUtils.takeScreenshot;

import io.qameta.allure.Allure;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class AllureAttachmentUtils {

  public static void attachScreenshot(String testName) {
    try {
      Path screenshotPath = takeScreenshot(testName);
      if (screenshotPath != null) {
        Allure.addAttachment("Screenshot: " + testName,
            Files.newInputStream(screenshotPath));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}