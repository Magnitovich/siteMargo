package margo.controller.other;

import margo.service.sendEmail.ConnectWithUsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;

@Controller
public class ConnectionWithUsController {
    @Autowired
    private ConnectWithUsService service;
    @RequestMapping(value = "/connectWithSupplier", method = RequestMethod.GET)
    public ModelAndView about(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("other/connectWithUs");
        return modelAndView;
    }
    @RequestMapping(value = "/connectWith", method = RequestMethod.POST)
    public ModelAndView sendQuestion(@RequestParam(value = "name") String name,
                                     @RequestParam(value = "email") String email,
                                     @RequestParam(value = "question") String question
                                     ) throws MessagingException {
//        System.out.println("Name: "+name+" email: "+email+ ", question: "+question);
        service.sendQuestion(email,name,question);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

}
