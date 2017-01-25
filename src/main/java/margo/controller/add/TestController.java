//package margo.controller.add;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.util.List;
//
//@Controller
//public class TestController {
//
//    @RequestMapping(name = "/test", method = {RequestMethod.GET})
//    public ModelAndView seeTest(){
//        ModelAndView m = new ModelAndView();
//        m.setViewName("addFabric/testRadio");
//        return m;
//    }
//    @RequestMapping(name = "/addRadio", method = {RequestMethod.POST})
//    public ModelAndView add(@RequestParam(value = "rbn", required = false) String name){
//        ModelAndView modelAndView = new ModelAndView();
//        System.out.println("Type Radio: "+name);
//        modelAndView.setViewName("addFabric/testRadio");
//        return modelAndView;
//    }
//
//}
