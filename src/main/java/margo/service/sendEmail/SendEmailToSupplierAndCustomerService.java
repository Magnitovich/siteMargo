package margo.service.sendEmail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class SendEmailToSupplierAndCustomerService {
    @Value("${img.imageMail.path}")
    private String imagePath;
    @Value("${spring.mail.username}")
    private String emailFrom;
    @Autowired
    private JavaMailSender javaMailSender;

    private Double total= Double.valueOf(0);
    private Double value;

    public void sendOrderOnEmail(String emailTo, String nameUser, List<String> list) throws MessagingException {

        MimeMessage mimeMessage =  javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        String addressSupplier = "newbrain31@gmail.com";
        String[]emailAddressArray = new String[]{addressSupplier, emailTo};
        helper.setCc(emailAddressArray);
//        System.out.println("SendEmailToSupplierAndCustomerService");
    //        System.out.println("emailTo: "+emailTo+" Name: "+nameUser);

        String subject = "Добрый день "+nameUser+". Подтверждение Вашего заказа";
        helper.setSubject(subject);
//        System.out.println(subject);

        ArrayList nameArrays = new ArrayList();
        ArrayList photoArrays = new ArrayList();

        StringBuffer sb = new StringBuffer("<html><body><table width=\"70%\"><tr>" +"<td>Image</td>"+
                "<td>Название</td><td width=\"30%\">Описание</td><td width=\"10%\">Кол-во</td>" +
                "<td width=\"10%\">Цена</td><td width=\"10%\">Сумма</td></tr>");
        int i = -1;
        int numberPage=0;

        for (String properties : list) {
            String[] split = properties.split("_");
            String name = split[1];
            Double quantity = Double.valueOf(split[2]);
            String photo = split[3];
            String describe = split[5];
            double price = Double.parseDouble((split[4]));
            double valueInTable = quantity*price;
            double valueBeforeRound = quantity*price;
            //two signs after a comma
            value= new BigDecimal(valueBeforeRound).setScale(2, RoundingMode.UP).doubleValue();
            nameArrays.add("<tr><td><img src=\"http://localhost:8080/"+photo+"\" width=\"70%\" height=\"50%\">" +
                    "</td><td>"+name+" </td><td>"+describe+"</td><td> "+quantity+"m.</td>"+
//            nameArrays.add("<tr><td><img src=\"http://dreams.cfapps.io/"+photo+"\" width=\"30%\" height=\"30%\"></td><td>"+name+" </td><td> "+quantity+"pieces.</td>"+
                    "<td>  $"+price+"</td><td> $" +value+"</td></tr>");
            total=new BigDecimal(total+value).setScale(2, RoundingMode.UP).doubleValue();
            i++;
            numberPage++;
        }
//        System.out.println(total);
        for (int a= 0; a<=i; a++){
//            System.out.println("nameArrays.get(a) -"+nameArrays.get(a));
            sb.append(nameArrays.get(a));
        }
        sb.append("<tr><td colspan='5' align='right'>Итого:</td><td>"+total+"</td></tr>");
        sb.append("</table></body></html>");

        helper.setText("Plain message", sb.toString());
        helper.setFrom(emailFrom);

        javaMailSender.send(mimeMessage);
    }
}
