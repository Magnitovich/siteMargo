package margo.controller.add;


import margo.model.modelDTO.allCurtainsDTO.CurtainDTO;
import margo.service.exception.ExceptionAddCurtainService;
import margo.service.fabric.CurtainService;
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
public class AddFabricController {


    @Autowired
    private CurtainService curtainService;

    @Autowired
    private ExceptionAddCurtainService exceptionAddCurtainService;

    @Value("${img.curtain.path}")
    private String realObjectsPath;

    @Value("${img.curtain.relative.path}")
    private String relativeObjectsPath;


    @RequestMapping(value = "/addInfoAboutNewCurtain", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView seePageAddCurtain(@RequestParam(required = false) Long id) {

        if (id != null ) {
            CurtainDTO curtain = curtainService.viewSelectedCurtain(id);
            curtain.setIdForEditCurtain(id);
//            System.out.println("AddFabricController "+id);
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("comparePhotoNameWithDB", curtain);
            modelAndView.setViewName("allFabric/addFabric/addFabricNew");
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("allFabric/addFabric/addFabricNew");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/addSuccessfulCurtain", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView addInfoCars(@ModelAttribute("comparePhotoNameWithDB") CurtainDTO dto,
                                    BindingResult result,
                                    @RequestParam(required = false) String id) throws IOException {

   ;

        AddPattern addPattern = new AddPattern();
        addPattern.checkInformations(dto, realObjectsPath, relativeObjectsPath);

        if (dto.getIdForEditCurtain() != null ) {
            dto.setPhoto(addPattern.getNameFile());
            dto.setPhoto01(addPattern.getNameFile01());
            dto.setPhoto02(addPattern.getNameFile02());
            dto.setPhoto03(addPattern.getNameFile03());
            dto.setPhoto04(addPattern.getNameFile04());
            dto.setPhoto05(addPattern.getNameFile05());

            curtainService.editCurtain(dto);
            List<CurtainDTO> curtainDTOs = curtainService.seeAllModels();
            ArrayList colorModel =curtainService.seeColor();
            ArrayList paint =curtainService.seePaint();
            ArrayList structure =curtainService.seeStructure();
            ArrayList filterPrice = curtainService.seePrice();

            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("allCurtain",curtainDTOs);
            modelAndView.addObject("price",filterPrice);
            modelAndView.addObject("forColor",colorModel);
            modelAndView.addObject("forPaint",paint);
            modelAndView.addObject("forStructure",structure);
            modelAndView.setViewName("allFabric/curtainModel");
            return modelAndView;

        } else {
//            System.out.println(curtainDTO.getName() + "//PHOTO:= " + curtainDTO.getObjectPhotoCurtain().getOriginalFilename());
//            System.out.println(curtainDTO.getStructure() + "//PHOTO:= " + curtainDTO.getObjectPhotoCurtain01().getOriginalFilename());
//            System.out.println(curtainDTO.getHeight() + "//PHOTO:= " + curtainDTO.getObjectPhotoCurtain02().getOriginalFilename());
//            System.out.println(curtainDTO.getPaint() + "//PHOTO:= " + curtainDTO.getObjectPhotoCurtain03().getOriginalFilename());
//            System.out.println(curtainDTO.getColor() + "//PHOTO:= " + curtainDTO.getObjectPhotoCurtain04().getOriginalFilename());
//            System.out.println(curtainDTO.getName() + "//PHOTO:= " + curtainDTO.getObjectPhotoCurtain05().getOriginalFilename());

            try {
//                System.out.println();
//                System.out.println("addPattern.getNameFile():");
//                System.out.println(addPattern.getNameFile()+", ");
//                System.out.println(addPattern.getNameFile01()+", ");
//                System.out.println(addPattern.getNameFile02()+", ");
//                System.out.println(addPattern.getNameFile03()+", ");
//                System.out.println(addPattern.getNameFile04()+", ");
//                System.out.println(addPattern.getNameFile05()+", ");

//                exceptionAddCurtainService.compareEnterInfoAndInDB(nameFile, nameFile01, nameFile02, nameFile03,
//                        nameFile04, nameFile05,
                exceptionAddCurtainService.compareEnterInfoAndInDB(addPattern.getNameFile(), addPattern.getNameFile01(),
                        addPattern.getNameFile02(), addPattern.getNameFile03(), addPattern.getNameFile04(),
                        addPattern.getNameFile05(), dto.getName(), dto.getDescription(), dto.getStructure(),
                        dto.getPaint(), dto.getHeight(), dto.getColor(), dto.getQuantity(), dto.getPrice());

                List<CurtainDTO> curtainDTOs = curtainService.seeAllModels();
                ArrayList colorModel =curtainService.seeColor();
                ArrayList paint =curtainService.seePaint();
                ArrayList structure =curtainService.seeStructure();
                ArrayList filterPrice = curtainService.seePrice();

                ModelAndView modelAndView = new ModelAndView();
                modelAndView.addObject("allCurtain",curtainDTOs);
                modelAndView.addObject("price",filterPrice);
                modelAndView.addObject("forColor",colorModel);
                modelAndView.addObject("forPaint",paint);
                modelAndView.addObject("forStructure",structure);
                modelAndView.setViewName("allFabric/curtainModel");
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
        andView.setViewName("allFabric/addFabric/addFabricNew");
        return andView;

    }

    @ModelAttribute("comparePhotoNameWithDB")
    public CurtainDTO createModel() {
        return new CurtainDTO();
    }
}


