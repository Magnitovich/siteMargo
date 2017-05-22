package margo.controller.cart;

import margo.filter.CheckUserRegistration;
import margo.model.user.UserModel;
import margo.service.cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AllInformationsAboutCustomerController {

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

        if (userAuthentication() != "anonymousUser") {

            String userName = userAuthentication();
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
private String userAuthentication(){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String name = authentication.getName();
//    System.out.println("AllInformationsAboutCustomerController: "+name);
    return name;
//    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//    if (principal instanceof UserDetails) {
//      String username = ((UserDetails)principal).getUsername();
//        System.out.println("principal instanceof UserDetails "+username);
//        return username;
//    } else {
//        String username = principal.toString();
//        System.out.println("else: "+username);
//        return username;
//    }
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
