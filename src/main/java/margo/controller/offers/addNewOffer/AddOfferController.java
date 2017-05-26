package margo.controller.offers.addNewOffer;


import margo.controller.serviceMargo.ServiceMargoController;
import margo.model.modelDTO.allCurtainsDTO.AllFabricDTO;
import margo.model.modelDTO.serviceMargo.ServiceMargoDTO;
import margo.service.finishedProduct.MainFinishedService;
import margo.service.offer.SelectRepositoryService;
import margo.service.serviceMargo.AddEditServiceMargoService;
import margo.service.serviceMargo.ServiceMargo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.CrudRepository;
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

@Controller
public class AddOfferController {

    @Autowired
    private SelectRepositoryService repositoryService;
    @Autowired
    private MainFinishedService service;

    private String nameFile = null;
    private FileOutputStream fileOutputStream = null;


    @RequestMapping(value = "/addNewOffer", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView seePageAddCurtain(@RequestParam(required = false) Long id,
                                          @RequestParam(value = "part", required = false)String part) {
        ModelAndView modelAndView = new ModelAndView();
        System.out.println(id+" photo: "+part);
            AllFabricDTO margoDTO = service.findSelectedModel(id, repositoryService.selectRepository(part));
//            margoDTO.setIdForEditCurtain(id);
            modelAndView.addObject("comparePhotoNameWithDB", margoDTO);
            modelAndView.setViewName("offers/addNewOffer");
            return modelAndView;

    }

//    @RequestMapping(value = "/addSuccessfulServiceMargo", method = {RequestMethod.GET, RequestMethod.POST})
//    public ModelAndView addInfo(@ModelAttribute("comparePhotoNameWithDB") ServiceMargoDTO dto,
//                                    BindingResult result) throws IOException {
//        System.out.println("Add Service MArgo:");
//        if (!dto.getObjectPhotoCurtain().isEmpty()) {
//            File convertFileObjectYachts = new File(realObjectsPathServiceMargo +
//                    dto.getObjectPhotoCurtain().getOriginalFilename());
//            if (!convertFileObjectYachts.exists()) {
//                convertFileObjectYachts.createNewFile();
//            }
//            fileOutputStream = new FileOutputStream(convertFileObjectYachts);
//            fileOutputStream.write(dto.getObjectPhotoCurtain().getBytes());
//
//            nameFile = relativeObjectsPathServiceMargo + dto.getObjectPhotoCurtain().getOriginalFilename();
//        }
//        System.out.println("NameFile: = "+nameFile);
//        if (dto.getIdForEditCurtain() != null) {
//            dto.setPhoto(nameFile);
//
//            serviceMargo.editCurtain(dto);
//            return margoController.seeAllServiceMargo();
//        } else {
//            try {
//                addEditService.compareEnterInfoAndInDB(nameFile, dto.getName(), dto.getDescription(), dto.getQuantity(), dto.getPrice());
//                return margoController.seeAllServiceMargo();
//            } catch (RuntimeException r) {
//
//                result.rejectValue("name", "error.name", "Error: Name or Photo exist");
//                return viewException();
//            }
//            //если я получу ошибку между открытием и закрытием потока, то поток без finally не закроется
//            finally {
//                if (fileOutputStream != null) {
//                    fileOutputStream.close();
//                }
//            }
//        }
//    }

    public ModelAndView viewException() {
        ModelAndView andView = new ModelAndView();
        andView.setViewName("offers/addNewOffer");
        return andView;
    }

    @ModelAttribute("comparePhotoNameWithDB")
    public AllFabricDTO createModel() {
        return new AllFabricDTO();
    }
}

