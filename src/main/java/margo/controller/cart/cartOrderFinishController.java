package margo.controller.cart;

import margo.dao.cart.CustomerRepository;
import margo.filter.CheckUserRegistration;
import margo.model.cartOder.CustomerModel;
import margo.service.cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Controller
public class CartOrderFinishController {

    @Autowired
    private CheckUserRegistration checkUserRegistration;
    @Autowired
    private AllInformationsAboutCustomerController allInformation;
    @Autowired
    private CartService cartService;
    @Autowired
    private CustomerRepository customerRepository;


    @RequestMapping(value = "/buySuccessful", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView view(@RequestBody List<String > newArrays) throws MessagingException {
//        System.out.println(newArrays);

        if (checkUserRegistration.getUserName().length()>0){
            System.out.println("checkUserRegistration.getUserName()"+checkUserRegistration.getUserName());

        } else {
            CustomerModel customerModel = new CustomerModel();
//            SimpleDateFormat date = new SimpleDateFormat("dd.MM.yyyy HH:mm");
                Date date = new Date();
            customerModel.setNameCustomer(allInformation.getNameCustomer());
            customerModel.setEmailCustomer(allInformation.getEmailCustomer());
            customerModel.setPhoneCustomer(allInformation.getPhoneCustomer());
            customerModel.setAddressCustomer(allInformation.getAddressCustomer());
            customerModel.setOderDate(date);
            customerRepository.save(customerModel);

            for (String properties : newArrays) {
                String[] split = properties.split("_");
                String id = split[0];
                String name = split[1];
                Double quantity = Double.valueOf(split[2]);
                String photo = split[3];

                cartService.changeInfoInDB(customerModel, id,photo, name,quantity);
            }


//            System.out.println("allInformation.getNameCustomer() "+allInformation.getNameCustomer()+
//                    "  allInformation.getAddressCustomer() : "+allInformation.getAddressCustomer()+
//            "  allInformation.getPhoneCustomer() "+allInformation.getPhoneCustomer()+
//                    "  allInformation.getEmailCustomer() "+allInformation.getEmailCustomer());
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
