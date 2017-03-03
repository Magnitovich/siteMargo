package margo.service.exception;



import margo.model.modelDTO.allCurtainsDTO.OrderCurtainDTO;
import margo.model.modelDTO.allCurtainsDTO.UpholsteryFabricDTO;
import margo.service.fabric.OrderCurtainService;
import margo.service.fabric.UpholsteryFabricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ExceptionAddUpholsteryService {

    @Autowired
    private UpholsteryFabricService service;


    public void compareEnterInfoAndInDB(final String photo, final String photo01, final String photo02,
                                        final String photo03, final String photo04, final String photo05,
                                        final String name, final String describe, final String structure,
                                        final String paint, final String height,final String color,
                                                     final Double quantity, final BigDecimal price) {

        List<UpholsteryFabricDTO> listPhoto = service.viewPhoto(photo);
        List<UpholsteryFabricDTO> listName = service.viewName(name);

        if (listPhoto.size()==0 && listName.size()==0) {
//        if (listPhoto.size()==0 ) {

            service.addNewInformation(photo, photo01, photo02, photo03, photo04, photo05, name, describe,
                    structure, paint, height, color, quantity, price);
//            ModelAndView modelAndView = yachtsController.viewListYachts();

        } else {
            throw new RuntimeException("WOW");
        }
    }

}
