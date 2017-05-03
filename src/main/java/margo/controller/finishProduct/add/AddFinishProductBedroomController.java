package margo.controller.finishProduct.add;


import margo.controller.add.AddPattern;
import margo.controller.finishProduct.BedroomController;
import margo.model.modelDTO.allCurtainsDTO.AllFabricDTO;
import margo.service.finishedProduct.AddEditService;
import margo.service.finishedProduct.CheckRepositoryService;
import margo.service.finishedProduct.MainFinishedService;
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

@Controller
public class AddFinishProductBedroomController {


    @Autowired
    private BedroomController bedroomController;
    @Autowired
    private MainFinishedService mainFinishedService;
    @Autowired
    private AddEditService exceptionAddFinishProduct;
    @Autowired
    private CheckRepositoryService repositoryService;

    private final String bedroom = "bedroom";
    private final String cabinet = "cabinet";
    private final String guestroom = "guestroom";
    private final String children = "children";
    private final String kitchen = "kitchen";
    private final String lambr = "lambr";
    private final String curtFinish = "curtFinish";
    private final String tulleFinish = "tulleFinish";


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


    @RequestMapping(value = "/addInfoAboutNewFinishProduct", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView seePageAddCurtain(@RequestParam(required = false) Long id,
                                          @RequestParam(value = "part", required = false)String part) {
        ModelAndView modelAndView = new ModelAndView();
        checkTo = part;
        if (id != null ) {
            AllFabricDTO curtain = mainFinishedService.viewSelectedFinishProduct(id, repositoryService.selectRepository(part));
            curtain.setIdForEditCurtain(id);
            modelAndView.addObject("comparePhotoNameWithDB", curtain);
            modelAndView.setViewName("finishedProduct/add/addBedroom");
            return modelAndView;
        } else {
            modelAndView.setViewName("finishedProduct/add/addBedroom");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/addSuccessfulFinishProduct", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView addInfoCars(@ModelAttribute("comparePhotoNameWithDB") AllFabricDTO dto,
                                    BindingResult result) throws IOException {

        AddPattern addPattern = new AddPattern();
        if (checkTo.equals(bedroom)) {
            addPattern.checkInformations(dto, realObjectsPathBedroom, relativeObjectsPathBedroom);
        } else if (checkTo.equals(cabinet)){
            addPattern.checkInformations(dto, realObjectsPathCabinet, relativeObjectsPathCabinet);
        } else if (checkTo.equals(guestroom)) {
            addPattern.checkInformations(dto, realObjectsPathGuestroom, relativeObjectsPathGuestroom);
        } else if (checkTo.equals(children)) {
            addPattern.checkInformations(dto, realObjectsPathChildroom, relativeObjectsPathChildroom);
        }else if (checkTo.equals(kitchen)) {
            addPattern.checkInformations(dto, realObjectsPathKitchen, relativeObjectsPathKitchen);
        }else if (checkTo.equals(lambr)) {
            addPattern.checkInformations(dto, realObjectsPathLamr, relativeObjectsPathLambr);
        }else if (checkTo.equals(curtFinish)) {
            addPattern.checkInformations(dto, realObjectsPathCurtainFinishProduct, relativeObjectsPathCurtainFinishProduct);
        }else if (checkTo.equals(tulleFinish)) {
            addPattern.checkInformations(dto, realObjectsPathTulleFinishProduct, relativeObjectsPathTulleFinishProduct );
        }

        if (dto.getIdForEditCurtain() != null ) {
            dto.setPhoto(addPattern.getNameFile());
            dto.setPhoto01(addPattern.getNameFile01());
            dto.setPhoto02(addPattern.getNameFile02());
            dto.setPhoto03(addPattern.getNameFile03());
            dto.setPhoto04(addPattern.getNameFile04());
            dto.setPhoto05(addPattern.getNameFile05());

            exceptionAddFinishProduct.editCurtain(dto, repositoryService.selectRepository(checkTo));
            return bedroomController.showBedroom(checkTo);
        } else {
            try{
                exceptionAddFinishProduct.compareEnterInfoAndInDB(addPattern.getNameFile(), addPattern.getNameFile01(),
                        addPattern.getNameFile02(), addPattern.getNameFile02(), addPattern.getNameFile03(),
                        addPattern.getNameFile05(), dto.getName(), dto.getDescription(), dto.getStructure(),
                        dto.getPaint(), dto.getHeight(), dto.getColor(), dto.getQuantity(), dto.getPrice(), dto.getItIsSewed(),
                        checkTo, dto.getIdForEditCurtain());
                return bedroomController.showBedroom(checkTo);
            } catch (RuntimeException r){

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
        andView.setViewName("finishedProduct/add/addBedroom");
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


//    @RequestMapping(value = "/finishProduct/addSuccessful", method = {RequestMethod.GET, RequestMethod.POST})
//    public ModelAndView addInfoCars(@ModelAttribute("comparePhotoNameWithDB") AllFabricDTO dto,
//                                    BindingResult result,
//                                    @RequestParam(required = false) String id) throws IOException {
//
//            List<AllFabricDTO> curtainDTOs = addEditService.addEditForm(checkTo, result, dto);
//
//        return bedroomController.showBedroom();
//        System.out.println("Id addSuccessful: "+id);
//        ModelAndView modelAndView = new ModelAndView();
//        return modelAndView;
//    }