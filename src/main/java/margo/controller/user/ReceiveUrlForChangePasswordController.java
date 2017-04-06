package margo.controller.user;


import margo.dao.UserRepository;
import margo.model.user.UserModel;
import margo.service.adminService.AdminRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ReceiveUrlForChangePasswordController {

    @Autowired
    private UserRepository userRepository;

    private String verificationFromUI;

    @RequestMapping(value = "/changePassword", method ={RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    ModelAndView getAttr(@RequestParam(value="verification") String verification) {

        verificationFromUI = verification;
        List<UserModel> byVerification = userRepository.findByVerification(verification);

        if (byVerification.size() == 0) {
            ModelAndView modelAndView = new ModelAndView();

            modelAndView.setViewName("registration/failedRegistrations");
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView();
            System.out.println(verification);
            String congratulate = "We congratulate you!!! You successfully registered";
//            modelAndView.addObject("successful", congratulate);
            modelAndView.setViewName("user/formForChangePassword");
            return modelAndView;
        }
    }

    public String getVerificationFromUI() {
        return verificationFromUI;
    }
}
