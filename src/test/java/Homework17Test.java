import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
public class Homework17Test extends BaseTest {
    @Test
    public void addNewSong() throws InterruptedException {
        try {
            navigateToLoginPage();
            provideEmail("yelyzaveta.postnova@testpro.io");
            providePassword("YrkeNi92");
            login();
            searchSong("take my hand");
            viewAll();
            selectFirstItem();
            addSelectedSong();
            //createNewPlaylist("Brand New Playlist");
            Thread.sleep(1000);
            WebElement alertShow = driver.findElement(By.cssSelector("div.success.show"));
            Assert.assertEquals(alertShow.getText(), "Added 1 song into \"Lisa.\"");
            //deletePlaylist("Brand New Playlist");
        } catch (InterruptedException e) {
            System.out.println("Oops, here is an error: " + e);
        }

    }
}
