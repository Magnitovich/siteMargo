package margo.controller.finishProduct.add;


import margo.controller.add.AddPattern;
import margo.model.modelDTO.allCurtainsDTO.AllFabricDTO;
import margo.model.modelDTO.allCurtainsDTO.CurtainDTO;
import margo.service.exception.ExceptionAddCurtainService;
import margo.service.exception.finishProduct.ExceptionAddFinishProduct;
import margo.service.fabric.CurtainService;
import margo.service.finishedProduct.BedroomService;
import margo.service.finishedProduct.MainFinishedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AddFinishProductBedroomController {


    @Autowired
    private MainFinishedService mainFinishedService;
    @Autowired
    private BedroomService bedroomService;

    @Autowired
    private ExceptionAddFinishProduct exceptionAddCurtainService;

    @Value("${img.bedroom.path}")
    private String realObjectsPathBedroom;
    @Value("${img.bedroom.relative.path}")
    private String relativeObjectsPathBedroom;
    @Value("${img.cabinet.path}")
    private String realObjectsPathCabinet;
    @Value("${img.cabinet.relative.path}")
    private String relativeObjectsPathCabinet;
    @Value("${img.childroom.path}")
    private String realObjectsPathChildroom;
    @Value("${img.childroom.relative.path}")
    private String relativeObjectsPathChildroom;
    @Value("${img.curtainFinishProduct.path}")
    private String realObjectsPathCurtainFinishProduct;
    @Value("${img.curtainFinishProduct.relative.path}")
    private String relativeObjectsPathCurtainFinishProduct;
    @Value("${img.tulleFinishProduct.path}")
    private String realObjectsPathTulleFinishProduct;
    @Value("${img.tulleFinishProduct.relative.path}")
    private String relativeObjectsPathTulleFinishProduct;
    @Value("${img.guestroom.path}")
    private String realObjectsPathGuestroom;
    @Value("${img.guestroom.relative.path}")
    private String relativeObjectsPathGuestroom;
    @Value("${img.kitchen.path}")
    private String realObjectsPathKitchen;
    @Value("${img.kitchen.relative.path}")
    private String relativeObjectsPathKitchen;
    @Value("${img.lambr.path}")
    private String realObjectsPathLamr;
    @Value("${img.lambr.relative.path}")
    private String relativeObjectsPathLambr;

    private String checkTo;

    @RequestMapping(value = "/finishProduct/addInfoAboutNewFinishProduct", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView seePageAddCurtain(@RequestParam(required = false) Long id,
                                          @RequestParam(value = "part", required = false)String part) {
        ModelAndView modelAndView = new ModelAndView();
        checkTo = part;
        System.out.println("Part: "+checkTo);
        if (id != null ) {
            AllFabricDTO curtain = bedroomService.viewSelectedFinishProduct(id);
            curtain.setIdForEditCurtain(id);
//            System.out.println("AddFabricController "+id);
            modelAndView.addObject("comparePhotoNameWithDB", curtain);
            modelAndView.setViewName("finishedProduct/add/addBedroom");
            return modelAndView;

        } else {
            modelAndView.setViewName("finishedProduct/add/addBedroom");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/finishProduct/addSuccessful", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView addInfoCars(@ModelAttribute("comparePhotoNameWithDB") AllFabricDTO dto,
                                    BindingResult result,
                                    @RequestParam(required = false) String id) throws IOException {


//        AddPattern addPattern = new AddPattern();
//        addPattern.checkInformations(dto, realObjectsPath, relativeObjectsPath);

        ModelAndView modelAndView = new ModelAndView();
        MultipartFile photo = dto.getObjectPhotoCurtain();
        System.out.println();
        System.out.println("PHOTO: "+photo);
        System.out.println();
        return modelAndView;
//        if (dto.getIdForEditCurtain() != null ) {
//            dto.setPhoto(addPattern.getNameFile());
//            dto.setPhoto01(addPattern.getNameFile01());
//            dto.setPhoto02(addPattern.getNameFile02());
//            dto.setPhoto03(addPattern.getNameFile03());
//            dto.setPhoto04(addPattern.getNameFile04());
//            dto.setPhoto05(addPattern.getNameFile05());
//
//            curtainService.editCurtain(dto);
//            List<CurtainDTO> curtainDTOs = curtainService.seeAllModels();
//            ArrayList colorModel =curtainService.seeColor();
//            ArrayList paint =curtainService.seePaint();
//            ArrayList structure =curtainService.seeStructure();
//            ArrayList filterPrice = curtainService.seePrice();
//
//            ModelAndView modelAndView = new ModelAndView();
//            modelAndView.addObject("allCurtain",curtainDTOs);
//            modelAndView.addObject("price",filterPrice);
//            modelAndView.addObject("forColor",colorModel);
//            modelAndView.addObject("forPaint",paint);
//            modelAndView.addObject("forStructure",structure);
//            modelAndView.setViewName("allFabric/curtainModel");
//            return modelAndView;
//
//        } else {
//            try {
//                exceptionAddCurtainService.compareEnterInfoAndInDB(addPattern.getNameFile(), addPattern.getNameFile01(),
//                        addPattern.getNameFile02(), addPattern.getNameFile02(), addPattern.getNameFile03(),
//                        addPattern.getNameFile05(), dto.getName(), dto.getDescription(), dto.getStructure(),
//                        dto.getPaint(), dto.getHeight(), dto.getColor(), dto.getQuantity(), dto.getPrice());
//
//                List<CurtainDTO> curtainDTOs = curtainService.seeAllModels();
//                ArrayList colorModel =curtainService.seeColor();
//                ArrayList paint =curtainService.seePaint();
//                ArrayList structure =curtainService.seeStructure();
//                ArrayList filterPrice = curtainService.seePrice();
//
//                ModelAndView modelAndView = new ModelAndView();
//                modelAndView.addObject("allCurtain",curtainDTOs);
//                modelAndView.addObject("price",filterPrice);
//                modelAndView.addObject("forColor",colorModel);
//                modelAndView.addObject("forPaint",paint);
//                modelAndView.addObject("forStructure",structure);
//                modelAndView.setViewName("allFabric/curtainModel");
//                return modelAndView;
//            } catch (RuntimeException r) {
//
//                result.rejectValue("name", "error.name", "Error: Name or Photo exist");
////            return seePageAddYachts(yachtDTO.getName());
//                return viewException();
//            }
//            //если я получу ошибку между открытием и закрытием потока, то поток без finally не закроется
//            finally {
//                if (addPattern.getFileOutputStream() != null) {
//                    addPattern.getFileOutputStream().close();
//                }
//            }
//        }
    }

    public ModelAndView viewException() {

        ModelAndView andView = new ModelAndView();
        andView.setViewName("allFabric/addFabric/addFabricNew");
        return andView;

    }

    @ModelAttribute("comparePhotoNameWithDB")
    public AllFabricDTO createModel() {
        return new AllFabricDTO();
    }

    public String getCheckTo() {
        return checkTo;
    }
}


