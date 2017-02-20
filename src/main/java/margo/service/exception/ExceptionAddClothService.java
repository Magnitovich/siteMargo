package margo.service.exception;



import margo.model.modelDTO.allCurtainsDTO.ClothFabricDTO;
import margo.service.fabric.ClothFabricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ExceptionAddClothService {

    @Autowired
    private ClothFabricService service;


    public void compareEnterInfoAndInDB(final String photo, final String photo01, final String photo02,
                                        final String photo03, final String photo04, final String photo05,
                                        final String name, final String describe, final String structure,
                                        final String paint, final String height,final String color,
                                                     final Double quantity, final BigDecimal price) {

        List<ClothFabricDTO> listPhoto = service.viewPhoto(photo);
        List<ClothFabricDTO> listName = service.viewName(name);

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
