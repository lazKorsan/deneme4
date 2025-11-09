package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(id = "email")
    public WebElement mailBox;

    @FindBy(id = "password")
    public WebElement passwordBox;

    @FindBy(xpath = "//button[@class='btn btn-primary btn-block mt-20']")
    public WebElement submitButton;

	@FindBy(id = "username")
	private WebElement usernameInput;


	@FindBy(id = "password")
	private WebElement passwordInput;

	@FindBy(id = "loginButton")
	private WebElement loginButton;

	// Constructor
	public LoginPage(WebDriver driver) {
		super(driver);
	}

	// Login işlemi
	public void login(String studentMail, String password) {
		type(mailBox, studentMail);
		type(passwordBox, password);
		click(submitButton);
	}


}
