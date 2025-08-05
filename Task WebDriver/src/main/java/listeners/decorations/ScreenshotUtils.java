package listeners.decorations;

import com.codeborne.selenide.Screenshots;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.text.SimpleDateFormat;

public class ScreenshotUtils {
  private static final Logger logger = LogManager.getLogger(ScreenshotUtils.class);

  public static Path takeScreenshot(String testName) {
    try {
      java.io.File screenshot = Screenshots.takeScreenShotAsFile();
      if (screenshot != null) {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss")
            .format(new Date());
        Path destinationPath = Paths.get("target", "screenshots", testName +
            "," + timestamp + ".png");

        Files.createDirectories(destinationPath.getParent());

        Files.move(screenshot.toPath(), destinationPath);

        logger.error("Screenshot saved: {}", destinationPath);
        return destinationPath;
      }
    } catch (Exception e) {
      logger.error("Failed to capture screenshot for test [{}]: {}",
          testName, e.getMessage());
    }
    return null;
  }
}
