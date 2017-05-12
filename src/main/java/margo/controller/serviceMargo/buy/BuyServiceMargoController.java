package margo.controller.serviceMargo.buy;

import margo.model.interior.InteriorModel;
import margo.model.modelDTO.serviceMargo.ServiceMargoDTO;
import margo.service.serviceMargo.ServiceMargo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BuyServiceMargoController {
    @Autowired
    private ServiceMargo interiorService;

    @RequestMapping(value = "/buyServiceMargoProduct", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView seeSelectedCurtain(@RequestParam(value = "modelId", required = false)Long id){

        ModelAndView modelAndView = new ModelAndView();

        ServiceMargoDTO selectedModel = interiorService.findSelectedModel(id);
        modelAndView.addObject("selectedCurtain", selectedModel);
        modelAndView.setViewName("serviceMargo/buy/buyServiceMargoProduct");
        return modelAndView;
    }
}
