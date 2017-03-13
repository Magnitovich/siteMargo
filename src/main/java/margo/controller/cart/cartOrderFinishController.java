package margo.controller.cart;

import margo.filter.CheckUserRegistration;
import margo.service.cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import java.util.List;

@Controller
public class CartOrderFinishController {

    @Autowired
    private CheckUserRegistration checkUserRegistration;
    @Autowired
    private AllInformationsAboutCustomerController allInformation;
    @Autowired
    private CartService cartService;


    @RequestMapping(value = "/buySuccessful", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView view(@RequestBody List<String > newArrays) throws MessagingException {
//        System.out.println(newArrays);
        for (String properties : newArrays) {
            String[] split = properties.split("_");
            String id = split[0];
            String name = split[1];
            Double quantity = Double.valueOf(split[2]);
            String photo = split[3];

            cartService.changeInfoInDB(id,photo, name,quantity);
        }
        if (checkUserRegistration.getUserName().length()>0){
            System.out.println("checkUserRegistration.getUserName()"+checkUserRegistration.getUserName());

        } else {
            System.out.println("allInformation.getNameCustomer() "+allInformation.getNameCustomer()+
                    "  allInformation.getAddressCustomer() : "+allInformation.getAddressCustomer()+
            "  allInformation.getPhoneCustomer() "+allInformation.getPhoneCustomer()+
                    "  allInformation.getEmailCustomer() "+allInformation.getEmailCustomer());
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
