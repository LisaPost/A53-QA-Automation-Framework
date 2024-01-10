import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

import java.time.Duration;

public class BaseTest {
    String url;
    WebDriver driver;
    @DataProvider(name="LoginAndPlaylistExample")
    public Object[][] getDataFromDataProviders() {
        return new Object[][] {
                {"yelyzaveta.postnova@testpro.io", "YrkeNi92", "Playlist1"},
                {"demo@class.com", "te$t$tudent", "Playlist1"}
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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        url = BaseUrl;
        driver.get(BaseUrl);
    }
    public void provideEmail(String username) {
        WebElement emailField = driver.findElement(By.cssSelector("input[type='email']"));
        emailField.clear();
        emailField.sendKeys(username);
    }
    public void providePassword(String password) {
        WebElement passwordField = driver.findElement(By.cssSelector("input[type='password']"));
        passwordField.clear();
        passwordField.sendKeys(password);
    }
    public void login() {
        WebElement submit = driver.findElement(By.cssSelector("button[type='submit']"));
        submit.click();
    }
    public void createPlaylist(String playlistName) {
        WebElement createNewPlaylist = driver.findElement(By.xpath("//i[@data-testid='sidebar-create-playlist-btn']"));
        createNewPlaylist.click();
        WebElement createSimplePlaylist = driver.findElement(By.xpath("//li[@data-testid='playlist-context-menu-create-simple']"));
        createSimplePlaylist.click();
        WebElement typePlaylistName = driver.findElement(By.xpath("//section[@id='playlists']//input[@type='text']"));
        typePlaylistName.sendKeys(playlistName);
        typePlaylistName.sendKeys(Keys.ENTER);
    }
    public void selectPlaylist() {
        WebElement playlist = driver.findElement(By.xpath("//section[@id='playlists']//a[contains(text(),'Playlist1')]"));
        playlist.click();
    }
    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }
}