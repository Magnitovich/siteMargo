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

//    @Value("${spring.mail.username}")
//    private String emailFrom;

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendingMessage(String toAddress, String confirmationUrl, String subject) throws MessagingException {

        String emailFrom = "newspace-inf-ua@1gb.ua";

        MimeMessage mimeMessage =  javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setTo(toAddress);
        helper.setSubject(subject);
        helper.setSentDate(new Date());
        helper.setText("message", confirmationUrl.toString());

        helper.setFrom(emailFrom);

        javaMailSender.send(mimeMessage);

    }
}



