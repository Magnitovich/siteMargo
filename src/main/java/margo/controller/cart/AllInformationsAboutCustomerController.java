package margo.controller.cart;

import groovy.transform.ToString;
import margo.model.cartOder.cartDTO.CustomerDTO;
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

    private String nameCustomer = "";
    private String phoneCustomer = "";
    private String emailCustomer = "";
    private String addressCustomer = "";

    @RequestMapping(value = "/showAll", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView showAllForCustomer(@RequestParam(value = "NickNameCustomerForSend")String name,
                                           @RequestParam(value = "PhoneCustomerForSend")String phone,
                                           @RequestParam(value = "descriptionCustomerForSend")String addressDelivery,
                                           @RequestParam(value = "EmailCustomerForSend")String email){
        nameCustomer = name;
        phoneCustomer = phone;
        addressCustomer = addressDelivery;
        emailCustomer = email;
        System.out.println("Name: "+name+" Phone: "+phone+" Address: "+addressDelivery+" Email: "+email);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("NickNameCustomer", nameCustomer);
        modelAndView.addObject("PhoneCustomer", phoneCustomer);
        modelAndView.addObject("EmailCustomer", emailCustomer);
        modelAndView.addObject("descriptionCustomer", addressCustomer);
        modelAndView.setViewName("cart/cartFinishCustomer");
        return modelAndView;
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
//    @RequestMapping(value = "/showAll/customerInfo", method = { RequestMethod.POST}
//            , produces = MediaType.APPLICATION_JSON_VALUE)
//    public ModelAndView showAllForCustomer(@RequestParam(value = "NickName")String name,
//                                           @RequestParam(value = "Phone")String phone,
//                                           @RequestParam(value = "description")String addressDelivery,
//                                           @RequestParam(value = "Email")String email) {
//        System.out.println("Name: " + name + " Phone: " + phone + " Address: " + addressDelivery + " Email: " + email);
//        ModelAndView modelAndView = new ModelAndView();
//        return modelAndView;
//
//    }