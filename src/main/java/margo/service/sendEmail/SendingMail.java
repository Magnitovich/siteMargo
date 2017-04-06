package margo.service.sendEmail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

@Service
public class SendingMail {

    @Value("${spring.mail.username}")
    private String emailFrom;

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendingMessage(String toAddress, String confirmationUrl, String subject) throws MessagingException {

        MimeMessage mimeMessage =  javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setTo(toAddress);
//        String subject = "Confirmation Registration";
        helper.setSubject(subject);
        helper.setSentDate(new Date());
//        String confirmationUrl =  "<a href='" +
//                "http://dreams.cfapps.io/registrationConfirm.html?verification="
//                + token+"'>Click for end Registration</a>";
//        String confirmationUrl =  "<a href='" +
//                "http://localhost:8080/registrationConfirm.html?verification="
//                + token+"'>Click for end Registration</a>";
        helper.setText("message", confirmationUrl.toString());

        helper.setFrom(emailFrom);

        javaMailSender.send(mimeMessage);



    }

}



