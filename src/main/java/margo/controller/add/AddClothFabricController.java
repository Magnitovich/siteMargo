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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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

        String nameFile = null;
        String nameFile01 = null;
        String nameFile02 = null;
        String nameFile03 = null;
        String nameFile04 = null;
        String nameFile05 = null;
        FileOutputStream fileOutputStream = null;

        if (!dto.getObjectPhotoCurtain().isEmpty()) {
            File convertFileObjectCurtain = new File(realObjectsPath +
                    dto.getObjectPhotoCurtain().getOriginalFilename());

            if (!convertFileObjectCurtain.exists()) {
                convertFileObjectCurtain.createNewFile();
            }

            fileOutputStream = new FileOutputStream(convertFileObjectCurtain);
            fileOutputStream.write(dto.getObjectPhotoCurtain().getBytes());

            nameFile = relativeObjectsPath + dto.getObjectPhotoCurtain().getOriginalFilename();
        }
        if (!dto.getObjectPhotoCurtain01().isEmpty()) {
            File convertFileObjectYachts = new File(realObjectsPath +
                    dto.getObjectPhotoCurtain01().getOriginalFilename());

            if (!convertFileObjectYachts.exists()) {
                convertFileObjectYachts.createNewFile();
            }

            nameFile01 = relativeObjectsPath + dto.getObjectPhotoCurtain01().getOriginalFilename();
        }
        if (!dto.getObjectPhotoCurtain02().isEmpty()) {
            File convertFileObjectYachts = new File(realObjectsPath +
                    dto.getObjectPhotoCurtain02().getOriginalFilename());

            if (!convertFileObjectYachts.exists()) {
                convertFileObjectYachts.createNewFile();
            }

            nameFile02 = relativeObjectsPath + dto.getObjectPhotoCurtain02().getOriginalFilename();
        }
        if (!dto.getObjectPhotoCurtain03().isEmpty()) {
            File convertFileObjectYachts = new File(realObjectsPath +
                    dto.getObjectPhotoCurtain03().getOriginalFilename());

            if (!convertFileObjectYachts.exists()) {
                convertFileObjectYachts.createNewFile();
            }
            nameFile03 = relativeObjectsPath + dto.getObjectPhotoCurtain03().getOriginalFilename();
        }
        if (!dto.getObjectPhotoCurtain04().isEmpty()) {
            File convertFileObjectYachts = new File(realObjectsPath +
                    dto.getObjectPhotoCurtain04().getOriginalFilename());

            if (!convertFileObjectYachts.exists()) {
                convertFileObjectYachts.createNewFile();
            }
            nameFile04 = relativeObjectsPath + dto.getObjectPhotoCurtain04().getOriginalFilename();
        }
        if (!dto.getObjectPhotoCurtain05().isEmpty()) {
            File convertFileObjectYachts = new File(realObjectsPath +
                    dto.getObjectPhotoCurtain05().getOriginalFilename());

            if (!convertFileObjectYachts.exists()) {
                convertFileObjectYachts.createNewFile();
            }
            nameFile05 = relativeObjectsPath + dto.getObjectPhotoCurtain05().getOriginalFilename();
        }

        if (dto.getIdForEditCurtain() != null ) {
            dto.setPhoto(nameFile);
            dto.setPhoto01(nameFile01);
            dto.setPhoto02(nameFile02);
            dto.setPhoto03(nameFile03);
            dto.setPhoto04(nameFile04);
            dto.setPhoto05(nameFile05);

            service.editCurtain(dto);
            List<ClothFabricDTO> yachtDTOs = service.seeAllCloth();
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("allCloth", yachtDTOs);
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

                exceptionAddService.compareEnterInfoAndInDB(nameFile, nameFile01, nameFile02, nameFile03,
                        nameFile04, nameFile05, dto.getName(), dto.getDescription(),
                        dto.getStructure(), dto.getPaint(), dto.getHeight(),
                        dto.getColor(), dto.getQuantity(), dto.getPrice());

                List<ClothFabricDTO> list = service.seeAllCloth();

                ModelAndView modelAndView = new ModelAndView();
                modelAndView.addObject("allCloth", list);
                modelAndView.setViewName("allFabric/clothFabric");
                return modelAndView;
            } catch (RuntimeException r) {

                result.rejectValue("name", "error.name", "Error: Name or Photo exist");
//            return seePageAddYachts(yachtDTO.getName());
                return viewExeption();
            }
            //если я получу ошибку между открытием и закрытием потока, то поток без finally не закроется
            finally {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
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


