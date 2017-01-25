package margo.controller;

import margo.filter.CheckUserRegistration;
import margo.service.adminService.AdminRoleService;
import margo.service.sendEmail.SendingMail;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.UUID;


@Controller
public class LoginController {

//    @Autowired
//    private UserDetailsService userDetailsService;
    @Autowired
    private AdminRoleService adminRoleService;

    @Autowired
    private SendingMail sendingMail;

    @Autowired
    private CheckUserRegistration checkUserRegistration;

    @RequestMapping(value = "/login" , method = {RequestMethod.GET})  //I NOT WRITE METHOD POST
    public ModelAndView loginPage(){
        ModelAndView modelAndView = new ModelAndView();
//        if(checkUserRegistration.getAuthendificationNumber()==0){
//            throw new UserNotAuthorizedException();
//        }
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value = "/registrationPage", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView registrationPage(
            @RequestParam(value = "NickName") String name,
            @RequestParam(value = "signUpPassword") String password,
            @RequestParam(value = "Phone") String phone,
            @RequestParam(value = "Email") String email
            ) throws MessagingException {

        Integer activityFalse = 0;
        String verification = UUID.randomUUID().toString();  //for send email
        System.out.println("Nick= "+name+" pass= "+password+" phone ="+phone+" email= "+email+" verification"+verification);

        adminRoleService.addNewUser(name, password, phone, email, activityFalse, verification);
//
        sendingMail.sendingMessage(email, verification);

        System.out.println(name + " pass:= " + password+ " email: "+email);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/index");
        return modelAndView;

    }
//    @RequestMapping(value = "/name_duplication", method = RequestMethod.GET)
//    @ResponseBody
//    public String checkDuplicates(@RequestParam(value="name") String name) {
//        System.out.println(name);
//        return userDetailsService.loadUserByUsername(name) == null ? "0" : "1";
//    }

}