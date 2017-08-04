package margo.controller.add;


import margo.controller.aboutFabric.CurtainController;
import margo.model.modelDTO.allCurtainsDTO.AllFabricDTO;
import margo.model.modelDTO.allCurtainsDTO.CurtainDTO;
import margo.service.exception.ExceptionAddCurtainService;
import margo.service.fabric.CurtainService;
import margo.service.finishedProduct.MainFinishedService;
import margo.service.offer.SelectRepositoryService;
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
    private MainFinishedService mainFinishedService;
    @Autowired
    private CurtainService curtainService;
    @Autowired
    private SelectRepositoryService repositoryService;
    @Autowired
    private CurtainController curtainController;
    @Autowired
    private ExceptionAddCurtainService exceptionAddCurtainService;

    @Value("${img.cloth.path}")
    private String realClothObjectsPath;
    @Value("${img.cloth.relative.path}")
    private String relativeClothObjectsPath;
    @Value("${img.curtain.path}")
    private String realCurtainObjectsPath;
    @Value("${img.curtain.relative.path}")
    private String relativeCurtainObjectsPath;
    @Value("${img.orderCurtain.path}")
    private String realOrderCurtainObjectsPath;
    @Value("${img.orderCurtain.relative.path}")
    private String relativeOrderCurtainObjectsPath;
    @Value("${img.tulle.path}")
    private String realTulleObjectsPath;
    @Value("${img.tulle.relative.path}")
    private String relativeTulleObjectsPath;
    @Value("${img.upholsteryFabric.path}")
    private String realUpholsteryObjectsPath;
    @Value("${img.upholsteryFabric.relative.path}")
    private String relativeUpholsteryObjectsPath;

    final String clothFabric = "clothFabric";
    final String curtainFabric = "curtain";
    final String orderFabric = "orderCurtain";
    final String tulleFabric = "tulle";
    final String upholsteryFabric = "upholsteryFabric";

    private String checkTo;

    @RequestMapping(value = "/addInfoAboutNewCurtain", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView seePageAddCurtain(@RequestParam(required = false) Long id,
                                          @RequestParam(value = "part")String part) {
        ModelAndView modelAndView = new ModelAndView();
        if(part.equals(tulleFabric)) {
            modelAndView.setViewName("allFabric/addFabric/addTulleNew");
        } else if (part.equals(orderFabric)){
            modelAndView.setViewName("allFabric/addFabric/addOrderFabric");
        }
        else {

            modelAndView.setViewName("allFabric/addFabric/addFabricNew");
        }
        checkTo = part;
        if (id != null ) {
            AllFabricDTO curtain = mainFinishedService.viewSelectedFinishProduct(id, repositoryService.selectRepository(part));
            curtain.setIdForEditCurtain(id);
            modelAndView.addObject("comparePhotoNameWithDB", curtain);

            return modelAndView;

        } else {
//            modelAndView.setViewName("allFabric/addFabric/addFabricNew");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/addSuccessfulCurtain", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView addInfoCars(@ModelAttribute("comparePhotoNameWithDB") CurtainDTO dto,
                                    BindingResult result) throws IOException {

        AddPattern addPattern = new AddPattern();
        if (checkTo.equals(clothFabric)) {
            addPattern.checkInformations(dto, realClothObjectsPath, relativeClothObjectsPath);
        } else if (checkTo.equals(curtainFabric)){
            addPattern.checkInformations(dto, realCurtainObjectsPath, relativeCurtainObjectsPath);
        } else if (checkTo.equals(orderFabric)) {
            addPattern.checkInformations(dto, realOrderCurtainObjectsPath, relativeOrderCurtainObjectsPath);
        } else if (checkTo.equals(tulleFabric)) {
            addPattern.checkInformations(dto, realTulleObjectsPath, relativeTulleObjectsPath);
        }else if (checkTo.equals(upholsteryFabric)) {
            addPattern.checkInformations(dto, realUpholsteryObjectsPath, relativeUpholsteryObjectsPath);
        }
//        addPattern.checkInformations(dto, realObjectsPath, relativeObjectsPath);

        if (dto.getIdForEditCurtain() != null ) {
            dto.setPhoto(addPattern.getNameFile());
            dto.setPhoto01(addPattern.getNameFile01());
            dto.setPhoto02(addPattern.getNameFile02());
            dto.setPhoto03(addPattern.getNameFile03());
            dto.setPhoto04(addPattern.getNameFile04());
            dto.setPhoto05(addPattern.getNameFile05());

            curtainService.editCurtain(dto, repositoryService.selectRepository(checkTo));

            return curtainController.seeAllCurtain(checkTo);

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
                        dto.getPaint(), dto.getHeight(), dto.getColor(), dto.getQuantity(), dto.getPrice(), checkTo);


                return curtainController.seeAllCurtain(checkTo);
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


