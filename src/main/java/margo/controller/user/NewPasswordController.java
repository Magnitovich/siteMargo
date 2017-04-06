package margo.controller.user;

import margo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class NewPasswordController {

    @Autowired
    private ReceiveUrlForChangePasswordController changePasswordController;
    @Autowired
    private UserService userService;


    @RequestMapping(value = "/newPassword", method = {RequestMethod.POST})
    public @ResponseBody void receiveNewPassword (@RequestParam(value = "newPassword")String password){

        System.out.println("NewPasswordController");
        System.out.println("NewPass: "+password+" Verific: "+changePasswordController.getVerificationFromUI());

        userService.changePassword(changePasswordController.getVerificationFromUI(), password);
    }
}
