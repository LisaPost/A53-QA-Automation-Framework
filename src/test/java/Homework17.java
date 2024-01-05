import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
public class Homework17 extends BaseTest {
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
            createNewPlaylist("Brand New Playlist");
            Thread.sleep(1000);
            WebElement alertShow = driver.findElement(By.xpath("//div[class='success show']"));
            Assert.assertEquals(alertShow.getText(), "Added 1 song into \""+listName+"\".");
            deletePlaylist("Brand New Playlist");
        } catch (Exception e) {
            System.out.println("Oops, here is an error: " + e);
        }

    }
}
