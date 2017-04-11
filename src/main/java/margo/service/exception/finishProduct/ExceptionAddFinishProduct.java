package margo.service.exception.finishProduct;



import margo.dao.finishProduct.*;
import margo.model.allCurtains.CurtainModel;
import margo.model.finishedProduct.AllFinishProductModel;
import margo.model.finishedProduct.BedroomModel;
import margo.model.finishedProduct.CabinetModel;
import margo.model.modelDTO.allCurtainsDTO.CurtainDTO;
import margo.service.fabric.CurtainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ExceptionAddFinishProduct {

    @Autowired
    private CurtainService curtainService;

    @Autowired
    private BedroomRepository bedroomRepository;
    @Autowired
    private CabinetRepository cabinetRepository;
    @Autowired
    private ChildrenRepository childrenRepository;
    @Autowired
    private CurtainFinishRepository curtainFinishRepository;
    @Autowired
    private GuestroomRepository guestroomRepository;
    @Autowired
    private KitchenRepository kitchenRepository;
    @Autowired
    private LambrequinRepository lambrequinRepository;
    @Autowired
    private TulleFinishRepository tulleFinishRepository;

    private final String bedroom = "bedroom";
    private final String cabinet = "cabinet";
    private final String guestroom = "guestroom";
    private final String children = "children";
    private final String kitchen = "kitchen";
    private final String lambr = "lambr";
    private final String curtFinish = "curtFinish";
    private final String tulleFinish = "tulleFinish";

    public void compareEnterInfoAndInDB(final String photo, final String photo01, final String photo02,
                                        final String photo03, final String photo04, final String photo05,
                                        final String name, final String describe, final String structure,
                                        final String paint, final String height, final String color,
                                        final Double quantity, final BigDecimal price, String checkTo) {
        switch (checkTo) {
            case bedroom:
                BedroomModel bedroomModel = new BedroomModel();
                if((bedroomRepository.findByPhoto(photo)).size() == 0 && (bedroomRepository.findByName(name)).size() == 0){
                    addNewInfoInDB(photo, photo01, photo02, photo03, photo04, photo05,name, describe,
                            structure,paint,height,color,quantity,price, bedroomRepository, bedroomModel);
                    break;
                } else { throw new RuntimeException("WOW"); }

            case cabinet:
                CabinetModel model = new CabinetModel();
                if((cabinetRepository.findByPhoto(photo)).size() == 0 && (cabinetRepository.findByName(name)).size() == 0){
                    addNewInfoInDB(photo, photo01, photo02, photo03, photo04, photo05,name, describe,
                            structure,paint,height,color,quantity,price, cabinetRepository, model);
                    break;
                } else { throw new RuntimeException("WOW"); }

        }


    }
     public void addNewInfoInDB(final String photo, final String photo01, final String photo02,
                                final String photo03, final String photo04, final String photo05,
                                final String name, final String describe, final String structure,
                                final String paint, final String height, final String color,
                                final Double quantity, final BigDecimal price, PagingAndSortingRepository repository,
                                AllFinishProductModel curtainModel){


            curtainModel.setPhoto(photo);
            curtainModel.setPhoto01(photo01);
            curtainModel.setPhoto02(photo02);
            curtainModel.setPhoto03(photo03);
            curtainModel.setPhoto04(photo04);
            curtainModel.setPhoto05(photo05);

            curtainModel.setName(name);
            curtainModel.setDescription(describe);
            curtainModel.setStructure(structure);
            curtainModel.setPaint(paint);
            curtainModel.setHeight(height);
            curtainModel.setColor(color);
            curtainModel.setQuantity(quantity);
            curtainModel.setPrice(price);
            repository.save(curtainModel);

    }

}
