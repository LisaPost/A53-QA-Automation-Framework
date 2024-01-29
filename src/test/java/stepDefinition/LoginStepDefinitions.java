package stepDefinition;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.datatable.DataTable;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.HomePage;
import pages.LoginPage;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.time.Duration;
import java.util.Properties;

public class LoginStepDefinitions {
    //WebDriver driver;
    //WebDriverWait wait;
    String BaseUrl = "https://qa.koel.app/";

    /*@Before
    public void setUp() {
        //String browser = System.getProperty("browser", "chrome");
        String browser = System.getProperty("browser");
        driver = createWebDriver(browser);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }*/
    /*public static WebDriver createWebDriver(String browser) {
        if ("chrome".equalsIgnoreCase(browser)) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications");
            options.addArguments("--remote-allow-origins=*");
            return new ChromeDriver(options);
            //driver = new ChromeDriver(options);
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }*/

    /*@Given("I open browser")
    public void openBrowser() {
        //WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }*/

    //@And("I open Login page")
    @Given("I open Login page")
    public void openLoginPage() {
        //driver.get(BaseUrl);
        BaseDefinition.getThreadLocal().get(BaseUrl);
    }

    @When("I enter email {string}")
    public void enterEmail(String email) {
        //LoginPage loginPage = new LoginPage(driver);
        LoginPage loginPage = new LoginPage(BaseDefinition.getThreadLocal());
        loginPage.provideEmail(email);
    }

    @And("I enter password {string}")
    public void enterPassword(String password) {
        //LoginPage loginPage = new LoginPage(driver);
        LoginPage loginPage = new LoginPage(BaseDefinition.getThreadLocal());
        loginPage.providePassword(password);
    }

    @And("I submit")
    public void clickSubmit(){
        //LoginPage loginPage = new LoginPage(driver);
        LoginPage loginPage = new LoginPage(BaseDefinition.getThreadLocal());
        loginPage.clickSubmit();
    }

    @Then("I am logged in")
    public void userIsLoggedIn() {
        //HomePage homePage = new HomePage(driver);
        HomePage homePage = new HomePage(BaseDefinition.getThreadLocal());
        Assert.assertTrue(homePage.getUserAvatar().isDisplayed());
    }

    @Then("I am not logged in")
    public void userIsNotLoggedIn() {
        //Assert.assertEquals(driver.getCurrentUrl(), BaseUrl);
        Assert.assertEquals(BaseDefinition.getThreadLocal().getCurrentUrl(), BaseUrl);
    }

    /*@After
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }*/

    //new implementation with storing test data separately
    /*@When("I enter email {string}")
    public void enterEmail(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> row : data) {
            String emailKey = row.get("email");
            String email = getProperty(emailKey);
            System.out.println("Entering email: " + email);
        }
    }*/
    /*@And("I enter password {string}")
    public void enterPassword(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> row : data) {
            String passwordKey = row.get("password");
            String password = getProperty(passwordKey);
            System.out.println("Entering password: " + password);
        }
    }*/

    /*private String getProperty(String key) {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("testdata.properties")) {
            Properties properties = new Properties();
            properties.load(input);
            return properties.getProperty(key);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error reading property for key: " + key);
        }
    }*/
}
