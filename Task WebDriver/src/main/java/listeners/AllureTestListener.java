package listeners;

import static listeners.AllureAttachmentUtils.attachScreenshot;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class AllureTestListener implements ITestListener {

  @Override
  public void onTestFailure(ITestResult result) {
    String testName = result.getMethod().getMethodName();
    attachScreenshot(testName);
  }

  @Override
  public void onTestSkipped(ITestResult result) {
    String testName = result.getMethod().getMethodName();
    attachScreenshot(testName);
  }

  @Override
  public void onTestStart(ITestResult result) {
    // Событие перед тестом (необязательно)
  }

  @Override
  public void onTestSuccess(ITestResult result) {
    // Событие при успешном тесте (необязательно)
  }

  @Override
  public void onStart(ITestContext context) {
    // Событие перед запуском набора тестов (необязательно)
  }

  @Override
  public void onFinish(ITestContext context) {
    // Событие после завершения набора тестов (необязательно)
  }
}