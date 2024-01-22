import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URI;
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
        //WebDriverManager.firefoxdriver().setup();
    }

    @BeforeMethod
    @Parameters({"BaseUrl"})
    public void launchBrowser(String BaseUrl) throws MalformedURLException {
        driver = pickBrowser(System.getProperty("browser"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        actions = new Actions(driver);
        driver.get(BaseUrl);
    }
    public WebDriver pickBrowser(String browser) throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        String gridURL = "http://192.168.1.78:4444";
        switch(browser) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                return driver = new FirefoxDriver();
            case "MicrosoftEdge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--remote-allow-origins=*");
                return driver = new EdgeDriver(edgeOptions);
            case "grid-chrome":
                caps.setCapability("browserName", "chrome");
                return driver = new RemoteWebDriver(URI.create(gridURL).toURL(), caps);
            case "grid-firefox":
                caps.setCapability("browserName", "firefox");
                return driver = new RemoteWebDriver(URI.create(gridURL).toURL(), caps);
            case "grid-edge":
                caps.setCapability("browserName", "MicrosoftEdge");
                return driver = new RemoteWebDriver(URI.create(gridURL).toURL(), caps);
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*");
                return driver = new ChromeDriver(chromeOptions);
        }
    }

    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }
}