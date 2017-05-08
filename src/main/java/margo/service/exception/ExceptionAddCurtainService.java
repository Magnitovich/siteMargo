package margo.service.exception;



import margo.model.modelDTO.allCurtainsDTO.CurtainDTO;
import margo.service.fabric.CurtainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ExceptionAddCurtainService {

    @Autowired
    private CurtainService curtainService;


    public void compareEnterInfoAndInDB(final String photo, final String photo01, final String photo02,
                                        final String photo03, final String photo04, final String photo05,
                                        final String name, final String describe, final String structure,
                                        final String paint, final String height,final String color,
                                                     final Double quantity, final BigDecimal price) {

        List<CurtainDTO> listPhoto = curtainService.viewPhoto(photo);
        List<CurtainDTO> listName = curtainService.viewName(name);

        if (listPhoto.size()==0 && listName.size()==0) {
//        if (listPhoto.size()==0 ) {
//            System.out.println("ExceptionAddCurtainSERVICE"+"photo01:= "+photo+", photo02:= "+photo01);
//            System.out.println("photo03: "+photo02+", photo04: "+photo03);
//            System.out.println("photo05: "+photo04+", photo06: "+photo05);
            curtainService.addNewCurtain(photo, photo01, photo02, photo03, photo04, photo05, name, describe,
                    structure, paint, height, color, quantity, price);
//            ModelAndView modelAndView = yachtsController.viewListYachts();

        } else {
            throw new RuntimeException("WOW");
        }
    }

}
