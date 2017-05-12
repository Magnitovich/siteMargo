package margo.controller.serviceMargo;

import margo.model.modelDTO.serviceMargo.ServiceMargoDTO;
import margo.service.serviceMargo.ServiceMargo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ServiceMargoController {
    @Autowired
    private ServiceMargo serviceMargo;

    @RequestMapping(value = "/serviceMargo", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView seeAllServiceMargo(){
        ModelAndView modelAndView = new ModelAndView();
        Iterable<ServiceMargoDTO> iterable = serviceMargo.seeAll();
        modelAndView.addObject("allCurtain",iterable);
        modelAndView.setViewName("serviceMargo/serviceMargo");
        return modelAndView;
    }
}
