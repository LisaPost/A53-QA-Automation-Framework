package stepDefinitions;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.search.FromTerm;
import java.net.MalformedURLException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OutlookEmailAutomation {
    private BaseDefinition baseDefinition;
    public OutlookEmailAutomation() {
        baseDefinition = new BaseDefinition();
    }
    public void navigateToResetLink(String resetLink) throws MalformedURLException {
        baseDefinition.threadLocal.set(baseDefinition.pickBrowser(System.getProperty("browser")));
        baseDefinition.getThreadLocal().get(resetLink);
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
}