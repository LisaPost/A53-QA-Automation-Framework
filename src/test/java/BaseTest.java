import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;

public class BaseTest {
    WebDriver driver;
    String url = "https://qa.koel.app/";
    String listName;
    @BeforeSuite
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }
    @BeforeMethod
    public void launchBrowser() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }
    public void navigateToLoginPage() {
        driver.get(url);
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
    public void searchSong(String song) {
        WebElement searchField = driver.findElement(By.cssSelector("input[type='search']"));
        searchField.clear();
        searchField.sendKeys(song);
    }
    public void viewAll() {
        WebElement viewSelectedSong = driver.findElement(By.cssSelector("button[data-test='view-all-songs-btn']"));
        viewSelectedSong.click();
    }
    public void selectFirstItem() {
        WebElement selectFirstSong = driver.findElement(By.xpath("//section[@id='songResultsWrapper']//table[contains(@class, 'items')]//tr[1]"));
        selectFirstSong.click();
    }
    public void addSelectedSong() {
        WebElement tapAddAllButton = driver.findElement(By.cssSelector("button[class='btn-add-to']"));
        tapAddAllButton.click();

      //WebElement selectExistingPlaylist = driver.findElement(By.xpath("//section[@id='songResultsWrapper']//li[contains(@class,'playlist') and contains(text(),'Lisa')]"));
     // selectExistingPlaylist.click();
    }
    public void createNewPlaylist(String listName) {
        WebElement newPlaylist = driver.findElement(By.xpath("//section[@id='songResultsWrapper']//input[@type='text']"));
        newPlaylist.sendKeys(listName);
        WebElement savePlaylist = driver.findElement(By.xpath("//section[@id='songResultsWrapper']//button[@type='submit']"));
        savePlaylist.click();
    }
    public void deletePlaylist(String listName) {
        //String listNameToDel;
        WebElement findPlaylist = driver.findElement(By.xpath("//section[@id='playlists']//a[contains(text(),'"+listName+"')]"));
        findPlaylist.click();
        WebElement deletePlaylist = driver.findElement(By.xpath("//button[@class='del btn-delete-playlist']"));
        deletePlaylist.click();
        WebElement deleteSubmit = driver.findElement(By.xpath("//div[@class='dialog']//button[@class='ok']"));
        deleteSubmit.click();
    }
    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }
}