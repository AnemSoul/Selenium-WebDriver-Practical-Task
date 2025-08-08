package tests.ui_tests;

import framework.DriverManager;
import io.qameta.allure.testng.AllureTestNg;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import listeners.AllureConfig;
import listeners.AllureTestListener;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static listeners.AllureConfig.configureResultsDirectory;

@Listeners({AllureTestNg.class, AllureTestListener.class})
public abstract class BaseTest {

  @BeforeSuite
  public void configureAllureResultsDirectory() {
    try {
      // Указываем правильный путь для Allure результатов
      Path allureResultsPath = Paths.get("target", "allure-results");
      Files.createDirectories(allureResultsPath);

      // Установка системного свойства
      System.setProperty("allure.results.directory", allureResultsPath.toAbsolutePath().toString());

      System.out.println("Configured allure.results.directory = " + allureResultsPath.toAbsolutePath());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @BeforeClass
  public void setUp() {
    DriverManager.getInstance();
  }

  @AfterClass
  public void tearDown() {
    closeWebDriver();
  }
}
