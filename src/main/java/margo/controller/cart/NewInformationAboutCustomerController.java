package margo.controller.cart;

import margo.model.user.UserModel;
import margo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NewInformationAboutCustomerController {

    private String nameUser;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/newInfoAboutCustomer/info", method = {RequestMethod.POST})
    public
    @ResponseBody
    String change(@RequestParam(value = "name") String name) {
//        System.out.println("NewInformationAboutCustomerController");
//        System.out.println(name);
        nameUser = name;
        return nameUser;
    }

    @RequestMapping(value = "/newInfoAboutCustomer", method = {RequestMethod.GET})
    public ModelAndView change() {
        ModelAndView modelAndView = new ModelAndView();
        UserModel userModel = userService.seeSelectedUser(nameUser);
        modelAndView.addObject("takeInfoFromDB", userModel);
        modelAndView.setViewName("cart/order/changeInfoAboutCustomerInUserDB");
        return modelAndView;
    }


    @ModelAttribute("takeInfoFromDB")
    public UserModel createModel() {
        return new UserModel();
    }
}

