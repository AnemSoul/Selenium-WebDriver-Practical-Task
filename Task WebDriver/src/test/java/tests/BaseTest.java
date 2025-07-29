package tests;

import framework.DriverManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public abstract class BaseTest {

  @BeforeClass
  public void setUp() {
    DriverManager.getInstance();
  }

  @AfterClass
  public void tearDown() {
    closeWebDriver();
  }
}