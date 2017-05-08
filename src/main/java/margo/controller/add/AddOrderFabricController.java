package margo.controller.add;


import margo.model.modelDTO.allCurtainsDTO.OrderCurtainDTO;
import margo.service.exception.ExceptionAddOrderService;
import margo.service.fabric.OrderCurtainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AddOrderFabricController {


    @Autowired
    private OrderCurtainService service;

    @Autowired
    private ExceptionAddOrderService exceptionAddService;

    @Value("${img.orderCurtain.path}")
    private String realObjectsPath;

    @Value("${img.orderCurtain.relative.path}")
    private String relativeObjectsPath;


    @RequestMapping(value = "/addInfoAboutNewOrder", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView seePageAddCurtain(@RequestParam(required = false) Long id) {

        if (id != null ) {
            OrderCurtainDTO model = service.viewSelected(id);
            model.setIdForEditCurtain(id);
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("comparePhotoNameWithDB", model);
            modelAndView.setViewName("allFabric/addFabric/addOrderFabric");
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("allFabric/addFabric/addOrderFabric");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/addSuccessfulOrder", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView addInfoCars(@ModelAttribute("comparePhotoNameWithDB") OrderCurtainDTO dto,
                                    BindingResult result,
                                    @RequestParam(required = false) String id) throws IOException {

        AddPattern addPattern = new AddPattern();
        addPattern.checkInformations(dto, realObjectsPath, relativeObjectsPath);

        if (dto.getIdForEditCurtain() != null ) {

            dto.setPhoto(addPattern.getNameFile());
            dto.setPhoto01(addPattern.getNameFile01());
            dto.setPhoto02(addPattern.getNameFile02());
            dto.setPhoto03(addPattern.getNameFile03());
            dto.setPhoto04(addPattern.getNameFile04());
            dto.setPhoto05(addPattern.getNameFile05());

            service.editCurtain(dto);
            List<OrderCurtainDTO> curtainDTOs = service.seeAllModels();
            ArrayList colorModel =service.seeColor();
            ArrayList paint =service.seePaint();
            ArrayList structure =service.seeStructure();
            ArrayList filterPrice = service.seePrice();

            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("price",filterPrice);
            modelAndView.addObject("forColor",colorModel);
            modelAndView.addObject("forPaint",paint);
            modelAndView.addObject("forStructure",structure);
            modelAndView.addObject("allOrder",curtainDTOs);
            modelAndView.setViewName("allFabric/orderFabric");
            return modelAndView;

        } else {

            try {

                        exceptionAddService.compareEnterInfoAndInDB(addPattern.getNameFile(), addPattern.getNameFile01(),
                                addPattern.getNameFile02(), addPattern.getNameFile03(), addPattern.getNameFile04(),
                                addPattern.getNameFile05(), dto.getName(), dto.getDescription(), dto.getStructure(),
                                dto.getPaint(), dto.getHeight(), dto.getColor(), dto.getQuantity(), dto.getPrice());

                List<OrderCurtainDTO> curtainDTOs = service.seeAllModels();
                ArrayList colorModel =service.seeColor();
                ArrayList paint =service.seePaint();
                ArrayList structure =service.seeStructure();
                ArrayList filterPrice = service.seePrice();

                ModelAndView modelAndView = new ModelAndView();
                modelAndView.addObject("price",filterPrice);
                modelAndView.addObject("forColor",colorModel);
                modelAndView.addObject("forPaint",paint);
                modelAndView.addObject("forStructure",structure);
                modelAndView.addObject("allOrder",curtainDTOs);
                modelAndView.setViewName("allFabric/orderFabric");
                return modelAndView;
            } catch (RuntimeException r) {

                result.rejectValue("name", "error.name", "Error: Name or Photo exist");
//            return seePageAddYachts(yachtDTO.getName());
                return viewException();
            }
            //если я получу ошибку между открытием и закрытием потока, то поток без finally не закроется
            finally {
                if (addPattern.getFileOutputStream() != null) {
                    addPattern.getFileOutputStream().close();
                }
            }
        }
    }
    public ModelAndView viewException() {

        ModelAndView andView = new ModelAndView();
        andView.setViewName("allFabric/addFabric/addOrderFabric");
        return andView;

    }

    @ModelAttribute("comparePhotoNameWithDB")
    public OrderCurtainDTO createModel() {
        return new OrderCurtainDTO();
    }
}


