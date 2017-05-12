package margo.controller.cart;

import margo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ChangeUserInfoInDBController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "changeUserInfo", method = RequestMethod.POST)
    public @ResponseBody void changeUser(@RequestParam(value = "nameCustomer")String name,
                                         @RequestParam(value = "phoneCustomer")String phone,
                                         @RequestParam(value = "addressCustomer")String address,
                                         @RequestParam(value = "emailCustomer")String email){
//        System.out.println("ChangeUserInfoInDBController");
//        System.out.println("Name: "+name+" Address: "+address+" Phone: "+phone+" email: "+email);
        userService.changeData(name,address,phone,email);
    }
}
