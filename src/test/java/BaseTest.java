import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver = null;
    protected WebDriverWait wait = null;
    protected Actions actions = null;

    @DataProvider(name = "LoginPositiveTest")
    public Object[][] getDataForPositiveLogin() {
        return new Object[][]{
                {"yelyzaveta.postnova@testpro.io", "YrkeNi92"}
        };
    }

    @DataProvider(name = "LoginNegativeTests")
    public Object[][] getDataForNegativeLogin() {
        return new Object[][]{
                {"yelyzaveta.postnova@testpro.io", "12345678", "https://qa.koel.app/"},
                {"nonperson@testpro.io", "YrkeNi92", "https://qa.koel.app/"},
                {"", "YrkeNi92", "https://qa.koel.app/"},
                {"yelyzaveta.postnova@testpro.io", "", "https://qa.koel.app/"},
                {"", "", "https://qa.koel.app/"}
        };
    }

    @DataProvider(name = "AddPlaylistPositiveTest")
    public Object[][] getDataForAddPlaylist() {
        return new Object[][]{
                {"yelyzaveta.postnova@testpro.io", "YrkeNi92", "New Playlist"}
        };
    }

    @DataProvider(name = "RenamePlaylistPositiveTest")
    public Object[][] getDataForRenamePlaylist() {
        return new Object[][]{
                {"yelyzaveta.postnova@testpro.io", "YrkeNi92", "New Playlist", "Renamed Playlist"}
        };
    }

    @DataProvider(name = "AddSongPositiveTest")
    public Object[][] getDataForAddSong() {
        return new Object[][]{
                {"yelyzaveta.postnova@testpro.io", "YrkeNi92", "New Playlist", "take my hand"}
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

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        actions = new Actions(driver);
        driver.get(BaseUrl);
    }

    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }
}