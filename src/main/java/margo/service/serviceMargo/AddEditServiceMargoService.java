package margo.service.serviceMargo;

import margo.model.modelDTO.interior.InteriorDTO;
import margo.model.modelDTO.serviceMargo.ServiceMargoDTO;
import margo.service.interior.InteriorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AddEditServiceMargoService {

    @Autowired
    private ServiceMargo service;

    public void compareEnterInfoAndInDB(final String photo, final String name, final String describe,
                                        final Double quantity, final BigDecimal price) {

        List<ServiceMargoDTO> listPhoto = service.viewPhoto(photo);
        List<ServiceMargoDTO> listName = service.viewName(name);

        if (listPhoto.size()==0 && listName.size()==0) {

            service.addNewInformation(photo, name, describe, quantity, price);
        } else {
            throw new RuntimeException("WOW");
        }
    }

}


