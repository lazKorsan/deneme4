package stepdefinitons;

import drivers.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.ManagementPage;
import utils.MultiScreenShootsMethods;

import java.time.Duration;

import static pages.ManagementPage.loginPageUrl;

public class exampleSteps {
    private WebDriver driver = DriverManager.getDriver(); // Driver'ı burada tanımla
    private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    ManagementPage managementPage = new ManagementPage(driver);



    @And("SCRENshots çeker")
    public void screnshotsCeker() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        MultiScreenShootsMethods.capturePageWithAnnotations(
                driver,
                loginPageUrl,
                // Kırmızı çerçevelenecekler
                new Object[][]{
                        {managementPage.mailBox, "mailBox"}
                },
                // Yeşil çerçevelenecekler
                new Object[][]{
                        {managementPage.passwordBox, "passwordBox"}
                }
        );
    }
}