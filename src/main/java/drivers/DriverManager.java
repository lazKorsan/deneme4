package drivers;

import config.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import utils.LoggerHelper;

import java.time.Duration;

public class DriverManager {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final int TIMEOUT = Integer.parseInt(ConfigReader.getProperty("timeout"));

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            String browser = ConfigReader.getProperty("browser").toLowerCase();
            LoggerHelper.info("Browser: " + browser);

            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--disable-search-engine-choice-screen");
                    chromeOptions.addArguments("--force-device-scale-factor=1.0");
                    chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                    // options.addArguments("--headless"); // Uncomment for headless execution
                    driver.set(new ChromeDriver(chromeOptions));
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver.set(new FirefoxDriver());
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driver.set(new EdgeDriver());
                    break;
                case "safari":
                    // WebDriverManager does not support Safari directly as it's bundled with the OS.
                    driver.set(new SafariDriver());
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }

            driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
            driver.get().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TIMEOUT));
            driver.get().manage().window().maximize();
            LoggerHelper.info("Driver started and configured with a timeout of " + TIMEOUT + " seconds.");
        }
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
            LoggerHelper.info("Driver quit successfully.");
        }
    }
}
