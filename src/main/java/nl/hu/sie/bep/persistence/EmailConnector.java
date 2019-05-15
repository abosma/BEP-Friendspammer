package nl.hu.sie.bep.persistence;

import io.github.cdimascio.dotenv.Dotenv;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

public class EmailConnector {

    private static Dotenv dotenv = Dotenv.load();

    private EmailConnector()
    {

    }

    public static Session getSession()
    {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.mailtrap.io");
        props.put("mail.smtp.port", "2525");
        props.put("mail.smtp.auth", "true");

        final String email_u = dotenv.get("EMAIL_USERNAME");
        final String email_p = dotenv.get("EMAIL_PASSWORD");

        Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(email_u, email_p);
                }
            }
        );

        return session;
    }
}
