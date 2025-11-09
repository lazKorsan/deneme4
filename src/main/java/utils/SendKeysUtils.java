package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SendKeysUtils {

    // ✅ ZORLA YAZMA (JS) - Etkileşime kapalı inputlar için
    public static void forceSendKeysWithJS(WebDriver driver, WebElement element, String text) {
        System.out.println("⚡️ JS ile zorla yazma başlatıldı: '" + text + "'");
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            ((JavascriptExecutor) driver).executeScript("arguments[0].value = arguments[1];", element, text);
            // Değişikliğin algılanması için 'change' event'ını tetikle
            ((JavascriptExecutor) driver).executeScript("arguments[0].dispatchEvent(new Event('change', { bubbles: true }));", element);
            System.out.println("✅ JS ile zorla yazma BAŞARILI!");
        } catch (Exception e) {
            System.out.println("❌ JS ile zorla yazma BAŞARISIZ: " + e.getMessage());
        }
    }

    // ✅ AKILLI SENDKEYS - Tüm senaryolar için
    public static void smartSendKeys(WebDriver driver, WebElement element, String text) {
        System.out.println("⌨️ SmartSendKeys başlatıldı: '" + text + "'");

        try {
            // 1. DENEME: Normal sendKeys
            element.clear();
            element.sendKeys(text);
            System.out.println("✅ Normal sendKeys BAŞARILI!");

        } catch (ElementNotInteractableException e) {
            // 2. DENEME: JS ile sendKeys
            System.out.println("🔄 Element etkileşime kapalı, JS ile yazılıyor...");
            sendKeysWithJS(driver, element, text);

        } catch (InvalidElementStateException e) {
            // 3. DENEME: Clear + sendKeys
            System.out.println("🔄 Element durumu geçersiz, temizleyip yazılıyor...");
            clearWithJS(driver, element);
            sendKeysWithJS(driver, element, text);

        } catch (StaleElementReferenceException e) {
            // 4. DENEME: Element yenilenmiş, tekrar denenmeli
            System.out.println("🔄 Element referansı geçersiz. Elementi yeniden bulup metodu tekrar çağırmalısınız.");
            // Bu hatayı çözmek için elementi test metodunuzda yeniden bulup bu metodu tekrar çağırmanız gerekir.
            throw e; // Hatayı yukarıya taşıyarak testin başarısız olmasını sağla
        }
    }

    // ✅ BEKLEMELİ SENDKEKS - DÜZELTİLMİŞ VERSİYON
    public static void smartSendKeysWithWait(WebDriver driver, WebElement element, String text, int seconds) {
        System.out.println("⏳ Beklemeli SmartSendKeys: '" + text + "'");

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
            wait.until(ExpectedConditions.elementToBeClickable(element));

            element.clear();
            element.sendKeys(text);
            System.out.println("✅ Beklemeli sendKeys BAŞARILI!");

        } catch (TimeoutException e) {
            System.out.println("❌ Element " + seconds + " saniyede hazır olmadı, JS ile yazılıyor...");
            sendKeysWithJS(driver, element, text);
        } catch (Exception e) {
            System.out.println("❌ Beklenmeyen hata, normal SmartSendKeys deneniyor...");
            smartSendKeys(driver, element, text);
        }
    }

    // ✅ YAVAŞ SENDKEYS - İnsan gibi yazar
    public static void slowSendKeys(WebDriver driver, WebElement element, String text, int delayMs) {
        System.out.println("🐌 Yavaş yazılıyor: '" + text + "'");

        try {
            element.clear();

            for (char c : text.toCharArray()) {
                element.sendKeys(String.valueOf(c));
                try {
                    Thread.sleep(delayMs);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println("✅ Yavaş sendKeys BAŞARILI!");

        } catch (Exception e) {
            System.out.println("❌ Yavaş yazma başarısız, normal yazılıyor...");
            smartSendKeys(driver, element, text);
        }
    }

    // ✅ TEMİZLE & YAZ - Önce temizler sonra yazar
    public static void clearAndSendKeys(WebDriver driver, WebElement element, String text) {
        System.out.println("🧹 Temizle & Yaz: '" + text + "'");

        try {
            element.clear();
            try {
                Thread.sleep(500);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
            element.sendKeys(text);
            System.out.println("✅ Clear & SendKeys BAŞARILI!");

        } catch (Exception e) {
            System.out.println("❌ Clear başarısız, JS ile temizlenip yazılıyor...");
            clearWithJS(driver, element);
            sendKeysWithJS(driver, element, text);
        }
    }

    // ✅ PRIVATE YARDIMCI METHODLAR
    private static void sendKeysWithJS(WebDriver driver, WebElement element, String text) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].value = arguments[1];", element, text);
            // Change event'ı tetikle (gerekliyse)
            js.executeScript("arguments[0].dispatchEvent(new Event('change', { bubbles: true }));", element);
            System.out.println("✅ JS sendKeys BAŞARILI!");
        } catch (Exception e) {
            System.out.println("❌ JS sendKeys de başarısız: " + e.getMessage());
        }
    }

    private static void clearWithJS(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value = '';", element);
    }
}
