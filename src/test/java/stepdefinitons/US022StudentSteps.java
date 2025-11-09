package stepdefinitons;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.ManagementPage;
import utils.ClickUtils;
import utils.ReusableMethods;
import utils.SendKeysUtils;

import java.time.Duration;

public class US022StudentSteps {

    private WebDriverWait wait;
    private ManagementPage managementPage;
    private WebDriver driver;
    public static final String LOGIN_PAGE_URL="https://qa.instulearn.com/login";
    public static final String teacherMail = "ahmet.teacher@instulearn.com";
    public static final String teacherPassword="Learn.1406";
    public static final String fileThumbnailPath="C:\\Users\\user\\Desktop\\instuLearn\\test1.jpeg";
    public static final String fileCoverImagePath="C:\\Users\\user\\Desktop\\instuLearn\\test2.jpeg";
    public static final String NEW_LESSON_TITLE="Math";
    public static final String step2Url="https://qa.instulearn.com/panel/webinars/3158/step/2";
    public static final String descriptionText="matematik ogrenmek çok güzel bir şeydir ";


    public US022StudentSteps() {
        this.driver = Hooks.getDriver();
        this.managementPage = new ManagementPage(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Bekleme süresini biraz daha artıralım
        PageFactory.initElements(driver, this.managementPage);
    }


    @Given("Kullanici login sayfasina gider")
    public void kullanici_login_sayfasina_gider() {

      driver.get(LOGIN_PAGE_URL);

    }
    @Given("Kullanici mailBox kutusuna mail adresi girer")
    public void kullanici_mail_box_kutusuna_mail_adresi_girer() {
        ClickUtils.smartClick(driver,managementPage.mailBox);
        managementPage.mailBox.sendKeys(teacherMail);

    }
    @Given("Kullanici passwordBox kutusuna password girer")
    public void kullanici_password_box_kutusuna_password_girer() {
        ClickUtils.smartClick(driver,managementPage.passwordBox);
        managementPage.passwordBox.sendKeys(teacherPassword);
       ;
    }
    @Given("Kullanici submitButon a basar")
    public void kullanici_submit_buton_a_basar() {
        managementPage.submitButton.click();
        ReusableMethods.bekle(5);

    }

    @When("Kullanici dashBoardMenude coursesButona tiklar")
    public void kullaniciDashBoardMenudeCoursesButonaTiklar() {

        Actions actions = new Actions(driver);
        actions.moveToElement(managementPage.dashboard).perform();
        ReusableMethods.bekle(2);





    }

    @When("Kullanici acilan menuden new secenegini tiklar")
    public void kullaniciAcilanMenudenNewSeceneginiTiklar() {

        ClickUtils.smartClick(driver,managementPage.coursesButton);
        ReusableMethods.bekle(2);
        ClickUtils.smartClick(driver,managementPage.newCoursesButton);


    }

    @When("Kullanici coursTypeContainer a tiklar")
    public void kullaniciCoursTypeContainerATiklar() {

        managementPage.coursTypeContainer.click();
        ReusableMethods.bekle(2);
    }

    @When("Kullanici videoCourse secenegine tiklar")
    public void kullaniciVideoCourseSecenegineTiklar() {
        managementPage.coursTypeContainer.click();
        ReusableMethods.bekle(2);
        managementPage.videoCourseOption.click();
        ReusableMethods.bekle(2);
    }

    @When("Kullanci basicInformationText uzerine tiklar")
    public void kullanciBasicInformationTextUzerineTiklar() {

    }

    @When("Kullanici titleBox a course turunu girer")
    public void kullaniciTitleBoxACourseTurunuGirer() {
        managementPage.newLessonTitleBox.sendKeys("Math");
    }

    @When("Kullanici seoMeataDescription bolumune acmak istedigi ders ile ilgili bilgi girer")
    public void kullaniciSeoMeataDescriptionBolumuneAcmakIstedigiDersIleIlgiliBilgiGirer() {
        ReusableMethods.bekle(1);
        SendKeysUtils.forceSendKeysWithJS(driver,managementPage.seoMateMessage,ManagementPage.newLessonMetaMassege);


    }

    @When("Kullanici thumbnail sekmesinden a resim yuklemesi yapar")
    public void kullaniciThumbnailSekmesindenAResimYuklemesiYapar() {
        SendKeysUtils.forceSendKeysWithJS(driver,managementPage.thumbnailBox,fileThumbnailPath);


    }

    @When("Kullanici coverImage sekmesinden resim yuklemsei yapar")
    public void kullaniciCoverImageSekmesindenResimYuklemseiYapar() {

        SendKeysUtils.forceSendKeysWithJS(driver,managementPage.coverImageBox,fileCoverImagePath);

    }

    @When("Kullanici descriptionBox a kurs aciklamasini girer")
    public void kullaniciDescriptionBoxAKursAciklamasiniGirer() {
        // 1. iframe'e geçiş yap (Genellikle bu editörler bir iframe içinde olur)
        driver.switchTo().frame(0); // Eğer sayfada tek iframe varsa index 0'dır.

        // 2. iframe içindeki body elementini bul
        WebElement editorBody = driver.findElement(By.tagName("body"));

        // 3. JavascriptExecutor ile metni gir
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].innerHTML = '" + descriptionText + "';", editorBody);

        // 4. Ana sayfaya geri dön
        driver.switchTo().defaultContent();
    }

    @When("Kullanici teacher newcourses sayfasindaki nextButona basar")
    public void kullaniciTeacherNewcoursesSayfasindakiNextButonaBasar() {
        // Butonun tıklanabilir olmasını bekle
        wait.until(ExpectedConditions.elementToBeClickable(managementPage.nextStepButton));
        
        // Tıklamayı JavaScript ile daha güçlü bir şekilde yap
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", managementPage.nextStepButton);
        
        // Bir sonraki sayfanın kilit elementinin (capacityBox) görünür olmasını bekle
        wait.until(ExpectedConditions.visibilityOf(managementPage.capacityBox));
    }

    @When("Kullanici capacityBox kutusuna ogrenci kapasitesi girer")
    public void kullaniciCapacityBoxKutusunaOgrenciKapasitesiGirer() {
        // Bekleme işlemini bir önceki adıma taşıdığımız için burada artık beklemeye gerek yok.
        managementPage.capacityBox.sendKeys("25");
    }

    @When("Kullanici durationOfNewCoursesBox a ders suresini girer")
    public void kullaniciDurationOfNewCoursesBoxADersSuresiniGirer() {
        managementPage.durationOfTimeBox.sendKeys("50");
    }

    @When("Kullanici supportSwitch butona basarak destek aktif eder")
    public void kullaniciSupportSwitchButonaBasarakDestekAktifEder() {
        ClickUtils.smartClick(driver,managementPage.supportSwitchButton);
        ReusableMethods.bekle(1);
    }

    @When("Kullanici completionCertifaeced butona basarak sertifika aktif eder")
    public void kullaniciCompletionCertifaecedButonaBasarakSertifikaAktifEder() {
        ClickUtils.smartClick(driver,managementPage.downloadAbleSwitchButon);
        ReusableMethods.bekle(1);
    }

    @When("Kullanici tagsBox kutusuna akilda kalici bir kelime girer")
    public void kullaniciTagsBoxKutusunaAkildaKaliciBirKelimeGirer() {
        managementPage.tagsBox.sendKeys("mathandFenbilgisi");

    }

    @When("Kullanici catogoryDrapDownMenu den secim yapar")
    public void kullaniciCatogoryDrapDownMenuDenSecimYapar() {
        ReusableMethods.selectByIndex(managementPage.coursesCotegoryButton,2);

    }

    @When("Kullanici editButona tiklar")
    public void kullaniciEditButonaTiklar() {
        wait.until(ExpectedConditions.visibilityOf(managementPage.editButton)).click();
    }

    @When("Kullanici sayfanın altında bulunan saveAsDraftButona basar")
    public void kullaniciSayfanınAltındaBulunanSaveAsDraftButonaBasar() {

        managementPage.saveAsDraftButton.click();
       // JavascriptExecutor js = (JavascriptExecutor) driver;
       // js.executeScript("arguments[0].click();", managementPage.saveAsDraftButton);

        // Bir sonraki sayfanın kilit elementinin (capacityBox) görünür olmasını bekle
        wait.until(ExpectedConditions.visibilityOf(managementPage.capacityBox));
    }

    @When("Kullanici svagButon uzerine navigasyon yapar ve tiklar")
    public void kullaniciSvagButonUzerineNavigasyonYaparVeTiklar() {

        ClickUtils.smartClickWithWait(driver,managementPage.svagButton,5);
    }


}
