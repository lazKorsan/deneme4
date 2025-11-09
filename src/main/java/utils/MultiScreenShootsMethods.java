package utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import drivers.DriverManager;
import org.openqa.selenium.*;
import org.testng.annotations.Test;
import pages.ManagementPage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static pages.ManagementPage.loginPageUrl;

public class MultiScreenShootsMethods {

    // < -- ========== METHOD ÇAĞIRILMASI SAYFANIN SONUNDA
    //  %%%QR,RED,GREEN

    private static final int RED_BORDER_THICKNESS = 5; // Kırmızı çerçevenin kalınlığı
    private static final int GREEN_BORDER_THICKNESS = 5; // Yeşil çerçevenin kalınlığı

    /**
     * Sayfa görüntüsü alır, URL ve QR kod ekler, belirtilen WebElement'leri canlı olarak
     * kırmızı veya yeşil çerçeve içine alır, altlarına açıklama yazar ve ekran görüntüsünü kaydeder.
     *
     * @param driver             WebDriver instance
     * @param url                Sayfanın URL'si
     * @param redBorderElements  Kırmızı çerçeve ve açıklama için {WebElement, String} çiftleri
     * @param greenBorderElements Yeşil çerçeve ve açıklama için {WebElement, String} çiftleri
     */
    public static void capturePageWithAnnotations(WebDriver driver,
                                                  String url,
                                                  Object[][] redBorderElements,
                                                  Object[][] greenBorderElements) {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        List<WebElement> createdElements = new ArrayList<>();
        List<Object[]> originalStyles = new ArrayList<>();

        try {
            // 1. Kırmızı çerçeveli elemanları ve açıklamalarını işle
            if (redBorderElements != null) {
                for (Object[] pair : redBorderElements) {
                    WebElement element = (WebElement) pair[0];
                    String description = (String) pair[1];
                    applyStyleAndDescription(driver, js, element, description, "red", originalStyles, createdElements);
                }
            }

            // 2. Yeşil çerçeveli elemanları ve açıklamalarını işle
            if (greenBorderElements != null) {
                for (Object[] pair : greenBorderElements) {
                    WebElement element = (WebElement) pair[0];
                    String description = (String) pair[1];
                    applyStyleAndDescription(driver, js, element, description, "green", originalStyles, createdElements);
                }
            }

            // 3. Canlı işaretlemeler yapıldıktan sonra ekran görüntüsü al
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            BufferedImage fullImg = ImageIO.read(screenshotFile);
            Graphics2D graphics = fullImg.createGraphics();

            // 4. Ekran görüntüsüne URL ve QR kod ekle (AWT ile)
            addUrlAndQrCode(graphics, url, fullImg.getWidth(), fullImg.getHeight());

            graphics.dispose();

            // 5. Sonucu dinamik yola kaydet
            saveScreenshot(fullImg);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 6. Sayfayı temizle: Eklenen açıklamaları kaldır ve orijinal stilleri geri yükle
            createdElements.forEach(el -> js.executeScript("arguments[0].parentNode.removeChild(arguments[0]);", el));
            originalStyles.forEach(pair -> {
                WebElement element = (WebElement) pair[0];
                String originalStyle = (String) pair[1];
                js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, originalStyle);
            });
        }
    }

    private static void applyStyleAndDescription(WebDriver driver, JavascriptExecutor js, WebElement element, String description, String color, List<Object[]> originalStyles, List<WebElement> createdElements) {
        js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'center'});", element);
        try { Thread.sleep(200); } catch (InterruptedException e) {} // Kaydırma sonrası render için kısa bekleme

        // Orijinal stili kaydet
        originalStyles.add(new Object[]{element, element.getAttribute("style")});

        // Yeni stili uygula
        String borderStyle = String.format("border: %dpx solid %s; box-sizing: border-box;",
                color.equals("red") ? RED_BORDER_THICKNESS : GREEN_BORDER_THICKNESS, color);
        js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, borderStyle);

        // Açıklama için yeni bir div oluştur ve sayfaya ekle
        WebElement descElement = (WebElement) js.executeScript(
                "var d = document.createElement('div');" +
                        "d.textContent = arguments[1];" +
                        "d.style.position = 'absolute';" +
                        "d.style.backgroundColor = 'red';" +
                        "d.style.color = 'white';" +
                        "d.style.padding = '2px 5px';" +
                        "d.style.borderRadius = '3px';" +
                        "d.style.zIndex = '10000';" +
                        "d.style.fontSize = '12px';" +
                        "var rect = arguments[0].getBoundingClientRect();" +
                        "d.style.left = (window.scrollX + rect.left) + 'px';" +
                        "d.style.top = (window.scrollY + rect.bottom + 5) + 'px';" +
                        "document.body.appendChild(d);" +
                        "return d;",
                element, description);
        createdElements.add(descElement);
    }

    private static void addUrlAndQrCode(Graphics2D graphics, String url, int imgWidth, int imgHeight) throws WriterException {
        // URL metnini sağ ortaya yaz
        Font font = new Font("Arial", Font.BOLD, 20);
        graphics.setFont(font);
        FontMetrics metrics = graphics.getFontMetrics(font);
        int textWidth = metrics.stringWidth(url);
        int xText = imgWidth - textWidth - 30;
        int yText = imgHeight / 2;
        graphics.setColor(Color.BLACK);
        graphics.fillRect(xText - 10, yText - metrics.getAscent(), textWidth + 20, metrics.getHeight());
        graphics.setColor(Color.WHITE);
        graphics.drawString(url, xText, yText);

        // QR Kodu oluştur ve URL'nin altına yerleştir
        int qrSize = 150;
        BufferedImage qrImage = generateQRCodeImage(url, qrSize, qrSize);
        int qrX = imgWidth - qrSize - 30;
        int qrY = yText + 10;
        graphics.drawImage(qrImage, qrX, qrY, null);
    }

    private static void saveScreenshot(BufferedImage image) throws IOException {
        String userHome = System.getProperty("user.home");
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = "Screenshot_" + timeStamp + ".png";
        String dynamicPath = userHome + File.separator + "Desktop" + File.separator + "instuLearn" + File.separator + fileName;

        File destinationFile = new File(dynamicPath);
        destinationFile.getParentFile().mkdirs(); // Gerekli klasörleri oluştur
        ImageIO.write(image, "png", destinationFile);
        System.out.println("Ekran görüntüsü kaydedildi: " + destinationFile.getAbsolutePath());
    }

    private static BufferedImage generateQRCodeImage(String text, int width, int height) throws WriterException {
        BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

    @Test
    public void multiScreenShotsMetodlarininCagirilmasi() {


        //

        ManagementPage stdunetPages = new ManagementPage(DriverManager.getDriver());

        DriverManager.getDriver().get(loginPageUrl);

        // cağırılan elementlerin kırmızı cizgiye alınması
        capturePageWithAnnotations(
                DriverManager.getDriver(),
                loginPageUrl,
                // Kırmızı çerçevelenecekler
                new Object[][]{
                        {stdunetPages.loginButton, "loginButton"}
                },
                // Yeşil çerçevelenecekler
                new Object[][]{
                        {stdunetPages.coursesButton2, "coursesButton"},

                }
        );

    }

}