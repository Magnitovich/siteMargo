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
public class ConnectWithUsService {

    @Value("${spring.mail.username}")
    private String emailFrom;

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendQuestion(String fromAddress, String name, String question) throws MessagingException {

        MimeMessage mimeMessage =  javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setTo(emailFrom);
        String subject = "Вопрос";
        helper.setSubject(subject);
        helper.setSentDate(new Date());
        StringBuffer sb = new StringBuffer("<html><body><table width=\"70%\"><tr><td>Имя</td>"+
            "<td>"+name+"</td></tr><tr><td>Email</td><td>"+fromAddress+"</td></tr><tr><td>" +
                "Вопрос</td><td>"+question+"</td></tr></table></body></html>");

        helper.setText("Plain message", sb.toString());
        helper.setFrom(emailFrom);

        javaMailSender.send(mimeMessage);



    }

}



