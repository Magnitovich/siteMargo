package margo.controller.cart;

import groovy.transform.ToString;
import margo.filter.CheckUserRegistration;
import margo.model.cartOder.cartDTO.CustomerDTO;
import margo.model.user.UserModel;
import margo.service.cart.CartService;
import margo.service.cart.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AllInformationsAboutCustomerController {

    @Autowired
    private CheckUserRegistration checkUserRegistration;
    @Autowired
    private CartService cartService;

    private String nameCustomer = "";
    private String phoneCustomer = "";
    private String emailCustomer = "";
    private String addressCustomer = "";

    @RequestMapping(value = "/showAll", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView showAllForCustomer(@RequestParam(value = "NickNameCustomerForSend", required = false) String name,
                                           @RequestParam(value = "PhoneCustomerForSend", required = false) String phone,
                                           @RequestParam(value = "descriptionCustomerForSend", required = false) String addressDelivery,
                                           @RequestParam(value = "EmailCustomerForSend", required = false) String email) {
        ModelAndView modelAndView = new ModelAndView();
        checkUserRegistration.checkAuthenticationUser();
        if (checkUserRegistration.getUserName() != null) {

            String userName = checkUserRegistration.getUserName();
//            System.out.println("checkUserRegistration.getUserName()" + checkUserRegistration.getUserName());
            UserModel userModel = cartService.ifUserAuthenticated(userName);
            nameCustomer = userModel.getName();
            phoneCustomer = userModel.getPhone();
            addressCustomer = userModel.getDescription();
            emailCustomer = userModel.getEmail();
            modelAndView.addObject("NickNameCustomer", nameCustomer);
            modelAndView.addObject("PhoneCustomer", phoneCustomer);
            modelAndView.addObject("EmailCustomer", emailCustomer);
            modelAndView.addObject("descriptionCustomer", addressCustomer);
            modelAndView.setViewName("cart/cartFinishCustomer");

            return modelAndView;

        } else {
            nameCustomer = name;
            phoneCustomer = phone;
            addressCustomer = addressDelivery;
            emailCustomer = email;


            modelAndView.addObject("NickNameCustomer", nameCustomer);
            modelAndView.addObject("PhoneCustomer", phoneCustomer);
            modelAndView.addObject("EmailCustomer", emailCustomer);
            modelAndView.addObject("descriptionCustomer", addressCustomer);
            modelAndView.setViewName("cart/cartFinishCustomer");
            return modelAndView;

        }
    }


    public String getNameCustomer() {
        return nameCustomer;
    }

    public String getPhoneCustomer() {
        return phoneCustomer;
    }

    public String getEmailCustomer() {
        return emailCustomer;
    }

    public String getAddressCustomer() {
        return addressCustomer;
    }
}
