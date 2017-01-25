//package margo.controller.add;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.List;
//
//@Controller
//public class AddFabricController {
//
//
//    @Autowired
//    private CurtainService curtainService;
//
//    @Autowired
//    private ExceptionAddCurtainService exceptionAddCurtainService;
//
//    @Value("${img.curtain.path}")
//    private String realObjectsPath;
//
//    @Value("${img.curtain.relative.path}")
//    private String relativeObjectsPath;
//
//
//    @RequestMapping(value = "/addInfoAboutNewCurtain", method = {RequestMethod.GET, RequestMethod.POST})
//    public ModelAndView seePageAddCurtain(@RequestParam(required = false) Long id) {
//
//        if (id != null ) {
//            CurtainDTO curtain = curtainService.viewSelectedCurtain(id);
//            curtain.setIdForEditCurtain(id);
//            System.out.println(id);
//            ModelAndView modelAndView = new ModelAndView();
//            modelAndView.addObject("comparePhotoNameWithDB", curtain);
//            modelAndView.setViewName("addEdit/addCurtains");
//            return modelAndView;
//
//        } else {
//            ModelAndView modelAndView = new ModelAndView();
//            modelAndView.setViewName("addEdit/addCurtains");
//            return modelAndView;
//        }
//    }
//
//    @RequestMapping(value = "/addSuccessfulCurtain", method = {RequestMethod.GET, RequestMethod.POST})
//    public ModelAndView addInfoCars(@ModelAttribute("addFabricNew") CurtainDTO curtainDTO,
//                                    BindingResult result,
//                                    @RequestParam(required = false) String id) throws IOException {
//
//        String nameFile = null;
//        String nameFile01 = null;
//        String nameFile02 = null;
//        String nameFile03 = null;
//        String nameFile04 = null;
//        String nameFile05 = null;
//        FileOutputStream fileOutputStream = null;
//
//        if (!curtainDTO.getObjectPhotoCurtain().isEmpty()) {
//            File convertFileObjectCurtain = new File(realObjectsPath +
//                    curtainDTO.getObjectPhotoCurtain().getOriginalFilename());
//
//            if (!convertFileObjectCurtain.exists()) {
//                convertFileObjectCurtain.createNewFile();
//            }
//
//            fileOutputStream = new FileOutputStream(convertFileObjectCurtain);
//            fileOutputStream.write(curtainDTO.getObjectPhotoCurtain().getBytes());
//
//            nameFile = relativeObjectsPath + curtainDTO.getObjectPhotoCurtain().getOriginalFilename();
//        }
//        if (!curtainDTO.getObjectPhotoCurtain01().isEmpty()) {
//            File convertFileObjectYachts = new File(realObjectsPath +
//                    curtainDTO.getObjectPhotoCurtain01().getOriginalFilename());
//
//            if (!convertFileObjectYachts.exists()) {
//                convertFileObjectYachts.createNewFile();
//            }
//
//            nameFile01 = relativeObjectsPath + curtainDTO.getObjectPhotoCurtain01().getOriginalFilename();
//        }
//        if (!curtainDTO.getObjectPhotoCurtain02().isEmpty()) {
//            File convertFileObjectYachts = new File(realObjectsPath +
//                    curtainDTO.getObjectPhotoCurtain02().getOriginalFilename());
//
//            if (!convertFileObjectYachts.exists()) {
//                convertFileObjectYachts.createNewFile();
//            }
//
//            nameFile02 = relativeObjectsPath + curtainDTO.getObjectPhotoCurtain02().getOriginalFilename();
//        }
//        if (!curtainDTO.getObjectPhotoCurtain03().isEmpty()) {
//            File convertFileObjectYachts = new File(realObjectsPath +
//                    curtainDTO.getObjectPhotoCurtain03().getOriginalFilename());
//
//            if (!convertFileObjectYachts.exists()) {
//                convertFileObjectYachts.createNewFile();
//            }
//            nameFile03 = relativeObjectsPath + curtainDTO.getObjectPhotoCurtain03().getOriginalFilename();
//        }
//        if (!curtainDTO.getObjectPhotoCurtain04().isEmpty()) {
//            File convertFileObjectYachts = new File(realObjectsPath +
//                    curtainDTO.getObjectPhotoCurtain04().getOriginalFilename());
//
//            if (!convertFileObjectYachts.exists()) {
//                convertFileObjectYachts.createNewFile();
//            }
//            nameFile04 = relativeObjectsPath + curtainDTO.getObjectPhotoCurtain04().getOriginalFilename();
//        }
//        if (!curtainDTO.getObjectPhotoCurtain05().isEmpty()) {
//            File convertFileObjectYachts = new File(realObjectsPath +
//                    curtainDTO.getObjectPhotoCurtain05().getOriginalFilename());
//
//            if (!convertFileObjectYachts.exists()) {
//                convertFileObjectYachts.createNewFile();
//            }
//            nameFile05 = relativeObjectsPath + curtainDTO.getObjectPhotoCurtain05().getOriginalFilename();
//        }
//
//        if (curtainDTO.getIdForEditCurtain() != null ) {
//            curtainDTO.setPhotoCurtain(nameFile);
//            curtainDTO.setPhotoCurtain01(nameFile01);
//            curtainDTO.setPhotoCurtain02(nameFile02);
//            curtainDTO.setPhotoCurtain03(nameFile03);
//            curtainDTO.setPhotoCurtain04(nameFile04);
//            curtainDTO.setPhotoCurtain05(nameFile05);
//
//            curtainService.editCurtain(curtainDTO);
//            List<CurtainDTO> yachtDTOs = curtainService.viewAllCurtain();
//            ModelAndView modelAndView = new ModelAndView();
//            modelAndView.addObject("margoCurtains", yachtDTOs);
//            modelAndView.setViewName("curtains");
//            return modelAndView;
//
//        } else {
//            System.out.println(curtainDTO.getNameCurtain() + "//PHOTO:= " + curtainDTO.getObjectPhotoCurtain().getOriginalFilename());
//            System.out.println(curtainDTO.getNameCurtain() + "//PHOTO:= " + curtainDTO.getObjectPhotoCurtain01().getOriginalFilename());
//            System.out.println(curtainDTO.getNameCurtain() + "//PHOTO:= " + curtainDTO.getObjectPhotoCurtain02().getOriginalFilename());
//            System.out.println(curtainDTO.getNameCurtain() + "//PHOTO:= " + curtainDTO.getObjectPhotoCurtain03().getOriginalFilename());
//            System.out.println(curtainDTO.getNameCurtain() + "//PHOTO:= " + curtainDTO.getObjectPhotoCurtain04().getOriginalFilename());
//            System.out.println(curtainDTO.getNameCurtain() + "//PHOTO:= " + curtainDTO.getObjectPhotoCurtain05().getOriginalFilename());
//
//            try {
//
//                exceptionAddCurtainService.compareEnterInfoAndInDB(nameFile, nameFile01, nameFile02, nameFile03,
//                        nameFile04,nameFile05, curtainDTO.getNameCurtain(),
//                        curtainDTO.getDescriptionsCurtain(), curtainDTO.getQuantityCurtain(), curtainDTO.getPriceCurtain());
//
//                List<CurtainDTO> list = curtainService.viewAllCurtain();
//
//                ModelAndView modelAndView = new ModelAndView();
//                modelAndView.addObject("margoCurtains", list);
//                modelAndView.setViewName("curtains");
//                return modelAndView;
//            } catch (RuntimeException r) {
//
//                result.rejectValue("name", "error.name", "Error: Name or Photo exist");
////            return seePageAddYachts(yachtDTO.getName());
//                return viewExeption();
//            }
//            //если я получу ошибку между открытием и закрытием потока, то поток без finally не закроется
//            finally {
//                if (fileOutputStream != null) {
//                    fileOutputStream.close();
//                }
//            }
//        }
//    }
//
//    public ModelAndView viewExeption() {
//
//        ModelAndView andView = new ModelAndView();
//        andView.setViewName("addEdit/addCurtains");
//        return andView;
//
//    }
//
//    @ModelAttribute("comparePhotoNameWithDB")
//    public CurtainDTO createModel() {
//        return new CurtainDTO();
//    }
//}
//
//
