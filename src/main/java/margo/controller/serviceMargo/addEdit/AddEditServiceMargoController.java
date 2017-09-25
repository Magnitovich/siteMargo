package margo.controller.serviceMargo.addEdit;


import margo.controller.serviceMargo.ServiceMargoController;
import margo.model.modelDTO.interior.InteriorDTO;
import margo.model.modelDTO.serviceMargo.ServiceMargoDTO;
import margo.service.interior.AddPatternForInterior;
import margo.service.serviceMargo.AddEditServiceMargoService;
import margo.service.serviceMargo.ServiceMargo;
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

@Controller
public class AddEditServiceMargoController {

    @Autowired
    private ServiceMargoController margoController;
    @Autowired
    private AddEditServiceMargoService addEditService;
    @Autowired
    private ServiceMargo serviceMargo;

    @Value("${img.serviceMargo.path}")
    private String realObjectsPathServiceMargo;
    @Value("${img.serviceMargo.relative.path}")
    private String relativeObjectsPathServiceMargo;

    private String nameFile = null;
    private FileOutputStream fileOutputStream = null;


    @RequestMapping(value = "/addInfoAboutNewServiceMargo", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView seePageAddCurtain(@RequestParam(required = false) Long id) {
        ModelAndView modelAndView = new ModelAndView();
        if (id != null) {
            ServiceMargoDTO margoDTO = serviceMargo.viewSelectedFinishProduct(id);
            margoDTO.setIdForEditCurtain(id);
            modelAndView.addObject("comparePhotoNameWithDB", margoDTO);
            modelAndView.setViewName("serviceMargo/addEdit/addEditServiceMargo");
            return modelAndView;
        } else {
            modelAndView.setViewName("serviceMargo/addEdit/addEditServiceMargo");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/addSuccessfulServiceMargo", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView addInfo(@ModelAttribute("comparePhotoNameWithDB") ServiceMargoDTO dto,
                                    BindingResult result) throws IOException {
        if (!dto.getObjectPhotoCurtain().isEmpty()) {
            File convertFileObjectYachts = new File(realObjectsPathServiceMargo +
                    dto.getObjectPhotoCurtain().getOriginalFilename());
            if (!convertFileObjectYachts.exists()) {
                convertFileObjectYachts.createNewFile();
            }
            fileOutputStream = new FileOutputStream(convertFileObjectYachts);
            fileOutputStream.write(dto.getObjectPhotoCurtain().getBytes());

            nameFile = relativeObjectsPathServiceMargo + dto.getObjectPhotoCurtain().getOriginalFilename();
        }
//        System.out.println("NameFile: = "+nameFile);
        if (dto.getIdForEditCurtain() != null) {
            dto.setPhoto(nameFile);

            serviceMargo.editCurtain(dto);
            return margoController.seeAllServiceMargo();
        } else {
            try {
                addEditService.compareEnterInfoAndInDB(nameFile, dto.getName(), dto.getDescription(), dto.getQuantity(), dto.getPrice());
                return margoController.seeAllServiceMargo();
            } catch (RuntimeException r) {

                result.rejectValue("name", "error.name", "Error: Name or Photo exist");
                return viewException();
            }
            //если я получу ошибку между открытием и закрытием потока, то поток без finally не закроется
            finally {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            }
        }
    }

    public ModelAndView viewException() {
        ModelAndView andView = new ModelAndView();
        andView.setViewName("serviceMargo/addEdit/addEditServiceMargo");
        return andView;
    }

    @ModelAttribute("comparePhotoNameWithDB")
    public ServiceMargoDTO createModel() {
        return new ServiceMargoDTO();
    }
}

