package margo.controller.add;


import margo.model.modelDTO.allCurtainsDTO.ClothFabricDTO;
import margo.service.exception.ExceptionAddClothService;
import margo.service.fabric.ClothFabricService;
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
public class AddClothFabricController {


    @Autowired
    private ClothFabricService service;

    @Autowired
    private ExceptionAddClothService exceptionAddService;

    @Value("${img.cloth.path}")
    private String realObjectsPath;

    @Value("${img.cloth.relative.path}")
    private String relativeObjectsPath;


    @RequestMapping(value = "/addInfoAboutNewCloth", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView seePageAddCurtain(@RequestParam(required = false) Long id) {

        if (id != null ) {
            ClothFabricDTO model = service.viewSelectedCloth(id);
            model.setIdForEditCurtain(id);
            System.out.println(id);
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("comparePhotoNameWithDB", model);
            modelAndView.setViewName("allFabric/addFabric/addClothFabricNew");
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("allFabric/addFabric/addClothFabricNew");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/addSuccessful", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView addInfoCars(@ModelAttribute("comparePhotoNameWithDB") ClothFabricDTO dto,
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
            List<ClothFabricDTO> clothFabricDTOs = service.seeAllCloth();
            ArrayList colorModel =service.seeColor();
            ArrayList paint =service.seePaint();
            ArrayList structure =service.seeStructure();
            ArrayList filterPrice = service.seePrice();

            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("price",filterPrice);
            modelAndView.addObject("forColor",colorModel);
            modelAndView.addObject("forPaint",paint);
            modelAndView.addObject("forStructure",structure);
            modelAndView.setViewName("allFabric/tulleFabric");
            modelAndView.addObject("allCloth", clothFabricDTOs);
            modelAndView.setViewName("allFabric/clothFabric");
            return modelAndView;

        } else {
//            System.out.println(curtainDTO.getName() + "//PHOTO:= " + curtainDTO.getObjectPhotoCurtain().getOriginalFilename());
//            System.out.println(curtainDTO.getStructure() + "//PHOTO:= " + curtainDTO.getObjectPhotoCurtain01().getOriginalFilename());
//            System.out.println(curtainDTO.getHeight() + "//PHOTO:= " + curtainDTO.getObjectPhotoCurtain02().getOriginalFilename());
//            System.out.println(curtainDTO.getPaint() + "//PHOTO:= " + curtainDTO.getObjectPhotoCurtain03().getOriginalFilename());
//            System.out.println(curtainDTO.getColor() + "//PHOTO:= " + curtainDTO.getObjectPhotoCurtain04().getOriginalFilename());
//            System.out.println(curtainDTO.getName() + "//PHOTO:= " + curtainDTO.getObjectPhotoCurtain05().getOriginalFilename());

            try {

                        exceptionAddService.compareEnterInfoAndInDB(addPattern.getNameFile(), addPattern.getNameFile01(),
                                addPattern.getNameFile02(), addPattern.getNameFile02(), addPattern.getNameFile03(),
                                addPattern.getNameFile05(), dto.getName(), dto.getDescription(), dto.getStructure(),
                                dto.getPaint(), dto.getHeight(), dto.getColor(), dto.getQuantity(), dto.getPrice());

                List<ClothFabricDTO> list = service.seeAllCloth();

                List<ClothFabricDTO> clothFabricDTOs = service.seeAllCloth();
                ArrayList colorModel =service.seeColor();
                ArrayList paint =service.seePaint();
                ArrayList structure =service.seeStructure();
                ArrayList filterPrice = service.seePrice();

                ModelAndView modelAndView = new ModelAndView();
                modelAndView.addObject("price",filterPrice);
                modelAndView.addObject("forColor",colorModel);
                modelAndView.addObject("forPaint",paint);
                modelAndView.addObject("forStructure",structure);
                modelAndView.setViewName("allFabric/tulleFabric");
                modelAndView.addObject("allCloth", clothFabricDTOs);
                modelAndView.setViewName("allFabric/clothFabric");
                return modelAndView;
            } catch (RuntimeException r) {

                result.rejectValue("name", "error.name", "Error: Name or Photo exist");
//            return seePageAddYachts(yachtDTO.getName());
                return viewExeption();
            }
            //если я получу ошибку между открытием и закрытием потока, то поток без finally не закроется
            finally {
                if (addPattern.getFileOutputStream() != null) {
                    addPattern.getFileOutputStream().close();
                }
            }
        }
    }

    public ModelAndView viewExeption() {

        ModelAndView andView = new ModelAndView();
        andView.setViewName("allFabric/addFabric/addClothFabricNew");
        return andView;

    }

    @ModelAttribute("comparePhotoNameWithDB")
    public ClothFabricDTO createModel() {
        return new ClothFabricDTO();
    }
}


