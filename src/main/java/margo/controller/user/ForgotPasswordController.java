package margo.controller.user;

import margo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;

@Controller
public class ForgotPasswordController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/forgetPassword", method = RequestMethod.GET)
    public ModelAndView seePageForChangePassword(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/user/changePassword");
        return modelAndView;
    }
    @RequestMapping(value = "/emailToChange", method = RequestMethod.POST)
    public @ResponseBody
    void receiveEmailFromUI(@RequestParam(value = "Email")String email) throws MessagingException {

        userService.sendChangeFormToEmailUser(email);
    }

}
