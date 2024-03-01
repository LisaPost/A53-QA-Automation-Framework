package stepDefinitions;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.search.FromTerm;
import java.net.MalformedURLException;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OutlookEmailAutomation {
    //private BaseDefinition baseDefinition;
    // last change:
    //private WebDriver driver;
    // BaseDefinition baseDefinition, WebDriver driver added
    //public OutlookEmailAutomation(BaseDefinition baseDefinition, WebDriver driver) {
        //baseDefinition = new BaseDefinition();
        //this.baseDefinition = baseDefinition;
        //this.driver = driver;
    //}
    public void navigateToResetLink(String resetLink) throws MalformedURLException {
        //baseDefinition.threadLocal.set(baseDefinition.pickBrowser(System.getProperty("browser")));
        if (resetLink != null) {
            WebDriver driver = BaseDefinition.getThreadLocal();
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            //baseDefinition.getThreadLocal().get(resetLink);
            //BaseDefinition.getThreadLocal().get(resetLink);
            //jsExecutor.executeScript("window.open('" + resetLink + "', '_blank');");
            jsExecutor.executeScript("window.open('" + resetLink + "', '_self');");
            switchToNewTabOrWindow();
        } else {
            System.err.println("Reset link is null. Cannot navigate");
        }

    }
    public static String extractResetLinkFromEmail(String userEmail) {
        try {
            Properties props = new Properties();
            props.setProperty("mail.store.protocol", "imaps");
            Session session = Session.getInstance(props);

            Store store = session.getStore("imaps");
            store.connect("outlook.office365.com", "yelyzaveta.postnova@testpro.io", "stPGYWcp8TWf");

            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);

            Message[] messages = inbox.search(new FromTerm(new InternetAddress(userEmail)), inbox.getMessages());

            for (Message message : messages) {
                if (!message.isSet(Flags.Flag.SEEN)) {
                    String content = getTextFromMessage(message);

                    String resetLink = extractResetLink(content);
                    if (resetLink != null) {
                        return resetLink;
                    }
                }
            }

            inbox.close(false);
            store.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getTextFromMessage(Message message) throws Exception {
        Object content = message.getContent();
        if (content instanceof String) {
            return (String) content;
        } else if (content instanceof Multipart) {
            StringBuilder sb = new StringBuilder();
            Multipart multipart = (Multipart) content;
            for (int i = 0; i < multipart.getCount(); i++) {
                BodyPart bodyPart = multipart.getBodyPart(i);
                sb.append(bodyPart.getContent().toString());
            }
            return sb.toString();
        }
        return null;
    }

    private static String extractResetLink(String content) {
        Pattern pattern = Pattern.compile("(?<=href=\")[^\"]+");
        Matcher matcher = pattern.matcher(content);

        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }
    private void switchToNewTabOrWindow() {
        WebDriver driver = BaseDefinition.getThreadLocal();
        //Set<String> windowHandles = baseDefinition.getThreadLocal().getWindowHandles();
        //Set<String> windowHandles = BaseDefinition.getThreadLocal().getWindowHandles();
        Set<String> windowHandles = driver.getWindowHandles();
        for (String windowHandle : windowHandles) {
            //baseDefinition.getThreadLocal().switchTo().window(windowHandle);
            //BaseDefinition.getThreadLocal().switchTo().window(windowHandle);
            driver.switchTo().window(windowHandle);
        }
    }
}