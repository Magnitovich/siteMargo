package margo.service.interior;

import margo.model.modelDTO.interior.InteriorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AddEditInteriorService {

    @Autowired
    private InteriorService service;

    public void compareEnterInfoAndInDB(final String photo, final String photo01, final String photo02,
                                        final String name, final String describe, final String color,
                                        final Double quantity, final BigDecimal price) {

        List<InteriorDTO> listPhoto = service.viewPhoto(photo);
        List<InteriorDTO> listName = service.viewName(name);

        if (listPhoto.size()==0 && listName.size()==0) {

            service.addNewInformation(photo, photo01, photo02, name, describe, color, quantity, price);
        } else {
            throw new RuntimeException("WOW");
        }
    }

}


