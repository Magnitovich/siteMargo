package margo.service.finishedProduct;
import margo.dao.finishProduct.*;
import margo.model.allCurtains.CurtainModel;
import margo.model.finishedProduct.*;
import margo.model.modelDTO.allCurtainsDTO.AllFabricDTO;
import margo.model.modelDTO.allCurtainsDTO.CurtainDTO;
import margo.service.fabric.CurtainService;
import margo.service.finishedProduct.MainFinishedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AddEditService {

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

    private List<AllFabricDTO> allFabricDTOs = null;

    public List<AllFabricDTO> compareEnterInfoAndInDB(final String photo, final String photo01, final String photo02,
                                                      final String photo03, final String photo04, final String photo05,
                                                      final String name, final String describe, final String structure,
                                                      final String paint, final String height, final String color,
                                                      final Double quantity, final BigDecimal price, final String sewed,
                                                      String checkTo) {
        switch (checkTo) {
            case bedroom:
                BedroomModel bedroomModel = new BedroomModel();
                if((bedroomRepository.findByPhoto(photo)).size() == 0 && (bedroomRepository.findByName(name)).size() == 0){
                    addNewInfoInDB(photo, photo01, photo02, photo03, photo04, photo05,name, describe,
                            structure,paint,height,color,quantity,price, sewed, bedroomRepository, bedroomModel);
                    break;
                } else { throw new RuntimeException("WOW"); }

            case cabinet:
                CabinetModel cabinetModel = new CabinetModel();
                if((cabinetRepository.findByPhoto(photo)).size() == 0 && (cabinetRepository.findByName(name)).size() == 0){
                    addNewInfoInDB(photo, photo01, photo02, photo03, photo04, photo05,name, describe,
                            structure,paint,height,color,quantity,price, sewed, cabinetRepository, cabinetModel);
                    break;
                } else { throw new RuntimeException("WOW"); }

            case guestroom:
                GuestroomModel guestModel = new GuestroomModel();
                if((guestroomRepository.findByPhoto(photo)).size() == 0 && (guestroomRepository.findByName(name)).size() == 0){
                    addNewInfoInDB(photo, photo01, photo02, photo03, photo04, photo05,name, describe,
                            structure,paint,height,color,quantity,price, sewed, guestroomRepository, guestModel);
                    break;
                } else { throw new RuntimeException("WOW"); }

            case children:
                ChildrenroomModel childrenroomModel = new ChildrenroomModel();
                if((childrenRepository.findByPhoto(photo)).size() == 0 && (childrenRepository.findByName(name)).size() == 0){
                    addNewInfoInDB(photo, photo01, photo02, photo03, photo04, photo05,name, describe,
                            structure,paint,height,color,quantity,price, sewed, childrenRepository, childrenroomModel);
                    break;
                } else { throw new RuntimeException("WOW"); }

            case kitchen:
                KitchenModel kitchenModel = new KitchenModel();
                if((kitchenRepository.findByPhoto(photo)).size() == 0 && (kitchenRepository.findByName(name)).size() == 0){
                    addNewInfoInDB(photo, photo01, photo02, photo03, photo04, photo05,name, describe,
                            structure,paint,height,color,quantity,price, sewed, kitchenRepository, kitchenModel);
                    break;
                } else { throw new RuntimeException("WOW"); }

            case lambr:
                LambrequinModel lambrequinModel = new LambrequinModel();
                if((lambrequinRepository.findByPhoto(photo)).size() == 0 && (lambrequinRepository.findByName(name)).size() == 0){
                    addNewInfoInDB(photo, photo01, photo02, photo03, photo04, photo05,name, describe,
                            structure,paint,height,color,quantity,price, sewed, lambrequinRepository, lambrequinModel);
                    break;
                } else { throw new RuntimeException("WOW"); }

            case curtFinish:
                CurtainFinishedModel curtainFinishedModel = new CurtainFinishedModel();
                if((curtainFinishRepository.findByPhoto(photo)).size() == 0 && (curtainFinishRepository.findByName(name)).size() == 0){
                    addNewInfoInDB(photo, photo01, photo02, photo03, photo04, photo05,name, describe,
                            structure,paint,height,color,quantity,price, sewed, curtainFinishRepository, curtainFinishedModel);
                    break;
                } else { throw new RuntimeException("WOW"); }
            case tulleFinish:
                TulleFinishedModel model = new TulleFinishedModel();
                if((tulleFinishRepository.findByPhoto(photo)).size() == 0 && (tulleFinishRepository.findByName(name)).size() == 0){
                    addNewInfoInDB(photo, photo01, photo02, photo03, photo04, photo05,name, describe,
                            structure,paint,height,color,quantity,price, sewed, tulleFinishRepository, model);
                    break;
                } else { throw new RuntimeException("WOW"); }

        }
        return allFabricDTOs;
    }
    @Transactional
    public void addNewInfoInDB(final String photo, final String photo01, final String photo02,
                               final String photo03, final String photo04, final String photo05,
                               final String name, final String describe, final String structure,
                               final String paint, final String height, final String color,
                               final Double quantity, final BigDecimal price,
                               final String sewed, CrudRepository repository, AllFinishProductModel curtainModel){

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
        curtainModel.setItIsSewed(sewed);
        curtainModel.setQuantity(quantity);
        curtainModel.setPrice(price);
        repository.save(curtainModel);

//        Iterable<AllFinishProductModel> models = repository.findAll();
//        allFabricDTOs = mainFinishedService.convertListModelToDTO((List<AllFinishProductModel>) models);

    }
    @Transactional
    public void editCurtain(AllFabricDTO dto, CrudRepository repository) {
        AllFinishProductModel model = (AllFinishProductModel) repository.findOne(dto.getId());

        if (!StringUtils.isEmpty(dto.getPhoto())) {
            model.setPhoto(dto.getPhoto());
        }
        if (!StringUtils.isEmpty(dto.getPhoto01())) {
            model.setPhoto01(dto.getPhoto01());
        }
        if (!StringUtils.isEmpty(dto.getPhoto02())) {
            model.setPhoto02(dto.getPhoto02());
        }
        if (!StringUtils.isEmpty(dto.getPhoto03())) {
            model.setPhoto03(dto.getPhoto03());
        }
        if (!StringUtils.isEmpty(dto.getPhoto04())) {
            model.setPhoto04(dto.getPhoto04());
        }
        if (!StringUtils.isEmpty(dto.getPhoto05())) {
            model.setPhoto05(dto.getPhoto05());
        }
        model.setName(dto.getName());
        model.setDescription(dto.getDescription());
        model.setStructure(dto.getStructure());
        model.setPaint(dto.getPaint());
        model.setHeight(dto.getHeight());
        model.setColor(dto.getColor());
        model.setItIsSewed(dto.getItIsSewed());
        model.setQuantity(dto.getQuantity());
        model.setPrice(dto.getPrice());

        repository.save(model);
    }

}
