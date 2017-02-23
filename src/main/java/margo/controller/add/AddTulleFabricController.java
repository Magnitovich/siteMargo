package margo.controller.add;


import margo.model.modelDTO.allCurtainsDTO.ClothFabricDTO;
import margo.model.modelDTO.allCurtainsDTO.TulleDTO;
import margo.service.exception.ExceptionAddClothService;
import margo.service.exception.ExceptionAddTulleService;
import margo.service.fabric.ClothFabricService;
import margo.service.fabric.TulleService;
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
import java.util.List;

@Controller
public class AddTulleFabricController {


    @Autowired
    private TulleService service;

    @Autowired
    private ExceptionAddTulleService exceptionAddService;

    @Value("${img.tulle.path}")
    private String realObjectsPath;

    @Value("${img.tulle.relative.path}")
    private String relativeObjectsPath;


    @RequestMapping(value = "/addInfoAboutNewTulle", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView seePageAddCurtain(@RequestParam(required = false) Long id) {

        if (id != null ) {
            TulleDTO model = service.viewSelectedCloth(id);
            model.setIdForEditCurtain(id);
            System.out.println(id);
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("comparePhotoNameWithDB", model);
            modelAndView.setViewName("allFabric/addFabric/addTulleNew");
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("allFabric/addFabric/addTulleNew");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/addSuccessfulTulle", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView addInfoCars(@ModelAttribute("comparePhotoNameWithDB") TulleDTO dto,
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
            List<TulleDTO> yachtDTOs = service.seeAllModels();
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("allTulle", yachtDTOs);
            modelAndView.setViewName("allFabric/tulleFabric");
            return modelAndView;

        } else {

            try {

                        exceptionAddService.compareEnterInfoAndInDB(addPattern.getNameFile(), addPattern.getNameFile01(),
                                addPattern.getNameFile02(), addPattern.getNameFile02(), addPattern.getNameFile03(),
                                addPattern.getNameFile05(), dto.getName(), dto.getDescription(), dto.getStructure(),
                                dto.getPaint(), dto.getHeight(), dto.getColor(), dto.getQuantity(), dto.getPrice());

                List<TulleDTO> list = service.seeAllModels();

                ModelAndView modelAndView = new ModelAndView();
                modelAndView.addObject("allTulle", list);
                modelAndView.setViewName("allFabric/tulleFabric");
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
        andView.setViewName("allFabric/addFabric/addTulleNew");
        return andView;

    }

    @ModelAttribute("comparePhotoNameWithDB")
    public TulleDTO createModel() {
        return new TulleDTO();
    }
}

