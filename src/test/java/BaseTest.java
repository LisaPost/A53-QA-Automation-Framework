import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;

public class BaseTest {
    WebDriver driver;
    WebDriverWait wait;
    @DataProvider(name="LoginAndPlaylistExample")
    public Object[][] getDataFromDataProviders() {
        return new Object[][] {
                {"yelyzaveta.postnova@testpro.io", "YrkeNi92", "New Playlist"},
                {"demo@class.com", "te$t$tudent", "New Playlist"}
        };
    }
    @BeforeSuite
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }
    @BeforeMethod
    @Parameters({"BaseUrl"})
    public void launchBrowser(String BaseUrl) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(BaseUrl);
    }
    public void provideEmail(String username) {
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='email']")));
        emailField.clear();
        emailField.sendKeys(username);
    }
    public void providePassword(String password) {
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='password']")));
        passwordField.clear();
        passwordField.sendKeys(password);
    }
    public void login() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[type='submit']"))).click();
    }
    public void createPlaylist(String playlistName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@data-testid='sidebar-create-playlist-btn']"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@data-testid='playlist-context-menu-create-simple']"))).click();
        WebElement typePlaylistName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[@id='playlists']//input[@type='text']")));
        typePlaylistName.sendKeys(playlistName);
        typePlaylistName.sendKeys(Keys.ENTER);
    }
    public void selectPlaylist() {
        wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//section[@id='playlists']//a[contains(text(),'New Playlist')]"))).click();
    }
    public String getPlaylistDeletedMsg() {
        WebElement parentElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.success.show")));
        WebElement deletedPlaylistMsg = parentElement.findElement(By.xpath("//div[contains(text(),'Deleted playlist \"New Playlist.\"')]"));
        return deletedPlaylistMsg.getText();
    }
    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }
}