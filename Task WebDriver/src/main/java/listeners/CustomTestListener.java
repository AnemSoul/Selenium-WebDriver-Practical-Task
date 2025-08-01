package listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.nio.file.Path;
import java.nio.file.Paths;

import static com.codeborne.selenide.Screenshots.takeScreenShotAsFile;

public class CustomTestListener implements ITestListener {

  private static final Logger logger = LogManager.getLogger(CustomTestListener.class);

  @Override
  public void onStart(ITestContext context) {
    logger.info("Starting test run: {}", context.getName());
  }

  @Override
  public void onFinish(ITestContext context) {
    logger.info("Finished test run: {}", context.getName());
  }

  @Override
  public void onTestStart(ITestResult result) {
    logger.info("Starting test: {}", result.getMethod().getMethodName());
  }

  @Override
  public void onTestSuccess(ITestResult result) {
    logger.info("Test PASSED: {}", result.getMethod().getMethodName());
  }

  @Override
  public void onTestFailure(ITestResult result) {
    logger.error("Test FAILED: {}",
        result.getMethod()
            .getMethodName(), result.getThrowable());

    // Снимаем скриншот при провале теста
    Path screenshotPath = takeScreenshot(result.getMethod().getMethodName());
    if (screenshotPath != null) {
      logger.error("Screenshot saved at: {}", screenshotPath.toString());
    }
  }

  @Override
  public void onTestSkipped(ITestResult result) {
    logger.warn("Test SKIPPED: {}", result.getMethod().getMethodName());
  }

  private Path takeScreenshot(String testName) {
    try {
      java.io.File screenshot = takeScreenShotAsFile();
      if (screenshot != null) {
        Path destinationPath = Paths.get("target", "screenshots", testName + ".png");
        java.nio.file.Files.createDirectories(destinationPath.getParent());
        java.nio.file.Files.move(screenshot.toPath(), destinationPath);
        return destinationPath;
      }
    } catch (Exception e) {
      logger.error("Failed to capture screenshot: {}", e.getMessage());
    }
    return null;
  }
}