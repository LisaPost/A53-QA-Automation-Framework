package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.UUID;

public class ProfilePage extends BasePage{
    public ProfilePage(WebDriver givenDriver) {
        super(givenDriver);
        //PageFactory.initElements(givenDriver, this);
    }
    @FindBy(css = "[name='current_password']")
    public WebElement currentPasswordField;
    @FindBy(xpath = "//input[@id='inputProfileName']")
    public WebElement profileNameField;
    @FindBy(xpath = "//button[@class='btn-submit']")
    public WebElement saveProfileBtn;

    public String getEnteredProfileName() {
        return profileNameField.getAttribute("value");
    }
}
