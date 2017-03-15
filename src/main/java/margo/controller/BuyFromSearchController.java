package margo.controller;

import margo.model.modelDTO.allCurtainsDTO.*;
import margo.service.fabric.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BuyFromSearchController {
    @Autowired
    private CurtainService curtainService;
    @Autowired
    private TulleService tulleService;
    @Autowired
    private UpholsteryFabricService upholsteryFabricService;
    @Autowired
    private OrderCurtainService orderCurtainService;
    @Autowired
    private ClothFabricService clothFabricService;

    @RequestMapping(value = "/buySelectedFromSearch", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView seeSelectedCurtain(@RequestParam(value = "hiddenModelId", required = false) Long id,
                                           @RequestParam(value = "photoId") String photo) {
        System.out.println("REQUEST ID: " + id);
        System.out.println("Photo: " + photo);
        String[] split = photo.split("/");
        System.out.println("split[0] " + split[0] + " split[1] " + split[1] + " split[2] " + split[2]);
        ModelAndView modelAndView = new ModelAndView();
        switch (split[1]) {
            case "clothFabric":
                String cloth = clothFabricService.convertIdToName(id);
                List<ClothFabricDTO> dtos = clothFabricService.viewName(cloth);
                modelAndView.addObject("selected", dtos);
                modelAndView.setViewName("allFabric/buyFabric/buyClothFabric");
//                return modelAndView;
                break;
            case "curtain":
                String curtain = curtainService.convertIdToName(id);
                List<CurtainDTO> curtainDTOs = curtainService.viewName(curtain);
                modelAndView.addObject("selectedCurtain", curtainDTOs);
                modelAndView.setViewName("allFabric/buyFabric/buyCurtain");
                break;
            case "orderCurtain":
                String order = orderCurtainService.convertIdToName(id);
                List<OrderCurtainDTO> dtosOrder = orderCurtainService.viewName(order);
                modelAndView.addObject("selected", dtosOrder);
                modelAndView.setViewName("allFabric/buyFabric/buyTulleFabric");
                break;
            case "tulle":
                String tulle = tulleService.convertIdToName(id);
                List<TulleDTO> tulleDto = tulleService.viewName(tulle);
                modelAndView.addObject("selected", tulleDto);
                modelAndView.setViewName("allFabric/buyFabric/buyTulleFabric");
                break;
              case "upholsteryFabric":
                String up = upholsteryFabricService.convertIdToName(id);
                List<UpholsteryFabricDTO> upholstery = upholsteryFabricService.viewName(up);
                  modelAndView.addObject("selected", upholstery);
                  modelAndView.setViewName("allFabric/buyFabric/buyTulleFabric");
                break;

        }
        return modelAndView;
    }
}