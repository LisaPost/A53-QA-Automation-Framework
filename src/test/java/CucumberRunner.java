import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.TestNGCucumberRunner;
import org.testng.annotations.*;

@Test
@CucumberOptions(
        features = {"src/test/resources/features/Login.feature"},
        publish = true
)
public class CucumberRunner extends AbstractTestNGCucumberTests {

    private TestNGCucumberRunner testNGCucumberRunner;

    @BeforeClass(alwaysRun = true)
    public void setUpCucumber() {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }
    @Override
    @DataProvider(parallel = true)
    /*public Object[][] features() {
        return testNGCucumberRunner.provideScenarios();*/
    public Object[][] scenarios() {
        return super.scenarios();
    }
    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        testNGCucumberRunner.finish();
    }
}
