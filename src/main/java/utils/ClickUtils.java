package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ClickUtils {

    // ✅ ZORLA TIKLAMA (JS) - Overlay arkasındaki elementler için
    public static void forceClickWithJS(WebDriver driver, WebElement element) {
        System.out.println("⚡️ JS ile zorla tıklama başlatıldı...");
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            System.out.println("✅ JS ile zorla tıklama BAŞARILI!");
        } catch (Exception e) {
            System.out.println("❌ JS ile zorla tıklama BAŞARISIZ: " + e.getMessage());
        }
    }

    // ✅ BEKLEMELİ AKILLI TIKLAMA - EN SAĞLAM YÖNTEM
    public static void smartClickWithWait(WebDriver driver, WebElement element, int timeoutInSeconds) {
        System.out.println("⏳ Beklemeli SmartClick başlatıldı: " + element.getText());
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            smartClick(driver, element); // Bekleme sonrası normal akıllı tıklamayı çağır
            System.out.println("✅ Beklemeli SmartClick BAŞARILI!");
        } catch (TimeoutException e) {
            System.out.println("❌ Element " + timeoutInSeconds + " saniye içinde tıklanabilir olmadı. JS ile tıklanıyor...");
            clickWithJS(driver, element); // Son çare olarak JS ile tıkla
        } catch (Exception e) {
            System.out.println("❌ Beklemeli SmartClick sırasında beklenmedik bir hata oluştu: " + e.getMessage());
            clickWithJS(driver, element); // Hata durumunda JS ile tıkla
        }
    }

    // ✅ HER TÜRLÜ BUTON İÇİN ÇALIŞIR
    public static void smartClick(WebDriver driver, WebElement element) {
        System.out.println("🔹 Smart click başlatıldı: " + element.getText());

        try {
            // 1. DENEME: Normal click
            element.click();
            System.out.println("✅ Normal click BAŞARILI!");

        } catch (ElementNotInteractableException e) {
            // 2. DENEME: JS click
            System.out.println("🔄 Görünmeyen buton, JS ile tıklanıyor...");
            clickWithJS(driver, element);

        } catch (StaleElementReferenceException e) {
            // 4. DENEME: Yeniden bul + click
            System.out.println("🔄 Element kayboldu, yeniden bulunup tıklanıyor...");
            // Bu hatayı çözmek için elementi test metodunuzda yeniden bulup bu metodu tekrar çağırmanız gerekir.
            throw e;
        }
    }

    private static void clickWithJS(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    private static void scrollToElement(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
}
