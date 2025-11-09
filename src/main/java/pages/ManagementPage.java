package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ManagementPage extends BasePage {

    public static final String studentMail = "ahmet.student@instulearn.com";
    public static final String studentPassword = "Learn.1406";
    public static final String teacherMail = "ahmet.teacher@instulearn.com";
    public static final String teacherPassword = "Learn.1406";
    public static final String paymantMail="admin.ahmet@loyalfriendcare.com";
    public static final String loginPageUrl ="https://qa.instulearn.com/login";
    public static final String newLessonMetaMassege ="Canlı matematik derslerimizle, uzman eğitmenler eşliğinde interaktif bir öğrenme deneyimi yaşayın. Anlık sorularınızı sorarak konuları derinlemesine anlayın.";
    public static final String newLessonTitle ="Math";
    public static final String thumbnailUpLoadURL ="https://qa.instulearn.com/laravel-filemanager?type=file";
    public static final String cardNumber="4242424242424242";

    public ManagementPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(xpath = "//*[@id=\"panel_app\"]/div[2]/div[3]/section[2]/div[2]/div/div/div[2]/div[1]/div/button/svg")
    public WebElement svagButton;

    @FindBy(xpath = "//a[contains(@href, 'edit')]")
    public WebElement editButton;


    @FindBy(xpath = "//button[@id='getNextStep']")
    public WebElement getNextStepButton;


    @FindBy(xpath = "//select[@name='category_id']")
    public WebElement coursesCotegoryButton;


    @FindBy(xpath = "//input[@placeholder='Type tag name and press enter (Max : 5)']")
    public WebElement tagsBox;


    @FindBy(xpath = "//*[@id=\"webinarForm\"]/div[2]/div/div[6]/div/label")
    public WebElement downloadAbleSwitchButon;

    @FindBy(xpath = "(//input[@id='supportSwitch'])[1]")
    public WebElement supportSwitchButton;



    @FindBy(xpath = "(//input[@class='form-control '])[2]")
    public WebElement durationOfTimeBox;



    @FindBy(xpath = "(//input[@class='form-control '])[1]")
    public WebElement capacityBox;


    @FindBy(xpath = "//a[@href='/panel/webinars/3158/delete?redirect_to=/panel/webinars']")
    public WebElement deleteButton;


    //@FindBy(xpath = "//button[@id='saveAsDraft']")
    //public WebElement saveAsDraftButton;

    @FindBy(css = "#saveAsDraft")
    public WebElement saveAsDraftButton;

    @FindBy(xpath = "//a[@href='/panel/webinars/3158/step/0']")
    public WebElement publishButton;


    @FindBy(xpath = "//a[@href='/panel/webinars/3158/step/0']")
    public WebElement previousButton;

    @FindBy(xpath = "//button[@id='getNextStep']")
    public WebElement nextStepButton;


    @FindBy(xpath = "//input[@id='thumbnail']")
    public WebElement thumbnailBox;

    @FindBy(xpath = "//input[@id='cover_image']")
    public WebElement coverImageBox;




    // @FindBy(className = "cursor-pointer user-select-none d-flex xs-categories-toggle")
    //public WebElement catogoriesButton;


    //@FindBy(id = "getNextStep")
    //public WebElement nextStepButton;




    @FindBy(xpath = "//*[@id=\"webinarForm\"]/div[2]/div/div[2]/input")
    public WebElement newLessonTitleBox;


    @FindBy(xpath = "//*[@id=\"webinarForm\"]/div[3]/div/div/div/div[3]/div[1]")
    public WebElement descriptionBox;


    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div/div[2]/div[2]/a[1]")
    public WebElement loginButton;

    @FindBy(xpath = "//*[@id=\"navbarContent\"]/ul/li[3]/a")
    public WebElement coursesButton2;


    @FindBy(id = "email")
    public WebElement mailBox;

    @FindBy(id = "password")
    public WebElement passwordBox;

    @FindBy(xpath = "//button[@class='btn btn-primary btn-block mt-20']")
    public WebElement submitButton;

    @FindBy(xpath = "(//span[@class='font-14 text-dark-blue font-weight-500'])[9]")
    public WebElement marketingButton;

    @FindBy(xpath = "(//span[@class='font-14 text-dark-blue font-weight-500'])[1]")
    public WebElement dashboard;

    @FindBy(xpath = "//*[@id=\"panel-sidebar-scroll\"]/div[1]/div[2]/div/div/div/li[2]/a/span[2]")
    public WebElement coursesButton;

    @FindBy(linkText ="New")
    public WebElement newCoursesButton;

    @FindBy(xpath ="//*[@id=\"webinarForm\"]/div[2]/div/div[1]/select")
    public WebElement coursTypeContainer;



    @FindBy(xpath = "//*[@id=\"webinarForm\"]/div[2]/div/div[3]/input")
    public WebElement seoMateMessage;

    // ... diğer elementleriniz ...

    @FindBy(xpath = "//*[@id=\"webinarForm\"]/div[2]/div/div[1]/select/option[2]") // Bu XPath'i sayfanıza göre doğrulamanız gerekebilir.
    public WebElement videoCourseOption;

    @FindBy(xpath = "//div[@id='panelSidebar']")
    public WebElement coursesText;

    @FindBy(xpath = "(//button[@class='input-group-text panel-file-manager'])[1]")
    public WebElement thumbnailButton;

    @FindBy(xpath = "(//button[@class='input-group-text panel-file-manager'])[2]")
    public WebElement coverImageButton;

    // switchwindow özellikleri

    @FindBy(xpath = "//i[@class='fas fa-plus']")
    public WebElement plusButton;

    @FindBy(xpath = "(//*[@class='fab-button fab-action'])[1]")
    public WebElement SwitchWindow2UploadButton;

    @FindBy(xpath = "//*[@id=\"tree\"]/ul/li[2]/a")
    public WebElement folderButton;

    @FindBy(id = "upload-button")
    public WebElement uploadButton;

    @FindBy(xpath = "//*[@id=\"content\"]/a[2]")
    public WebElement useButton;

    @FindBy(xpath = "//*[@id=\"actions\"]/a[3]")
    public WebElement saveButton;

    @FindBy(xpath = "//*[@id=\"panel-sidebar-scroll\"]/div[1]/div[2]/div/div/div/li[11]/a/span[2]")
    public WebElement logoutButton;

    @FindBy(xpath = "//a[@href='/panel/webinars/new']")
    public WebElement newButton ;

    @FindBy(xpath = "//*[@id=\"panel-sidebar-scroll\"]/div[1]/div[2]/div/div/div/li[2]/a/span[2]")
    public WebElement teachersCoursesButton;

    @FindBy(xpath = "//*[@id=\"webinarForm\"]/div[1]/div[1]/div/h4")
    public WebElement basicInfoText;

    @FindBy(xpath = "//div[@class='note-editable card-block']")
    public WebElement descriptionBoxArea;









}
