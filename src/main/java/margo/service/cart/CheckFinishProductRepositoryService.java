package margo.service.cart;

import margo.dao.finishProduct.*;
import margo.dao.interior.InteriorRepository;
import margo.model.finishedProduct.AllFinishProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class CheckFinishProductRepositoryService {
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
    @Autowired
    private InteriorRepository interiorRepository;

    private final String bedroom = "bedroom";
    private final String cabinet = "cabinet";
    private final String children = "children";
    private final String guestroom = "guestroom";
    private final String kitchen = "kitchen";
    private final String lambr = "lambr";
    private final String curtFinish = "curtFinish";
    private final String tulleFinish = "tulleFinish";


    private AllFinishProductModel model = null;
    private CrudRepository repository = null;

    public CrudRepository getRepository() {
        return repository;
    }

    public AllFinishProductModel selectRepository(String part, String name) {
        switch (part) {
            case bedroom:
                repository = bedroomRepository;
                model = (bedroomRepository.findByName(name)).get(0);
            break;
            case cabinet:
                repository = cabinetRepository;
                model = (cabinetRepository.findByName(name)).get(0);
            break;
            case children:
                repository = cabinetRepository;
                model = (childrenRepository.findByName(name)).get(0);
            break;
            case guestroom:
                repository = guestroomRepository;
                model = (guestroomRepository.findByName(name)).get(0);
            break;
            case kitchen:
                repository = kitchenRepository;
                model = (kitchenRepository.findByName(name)).get(0);
            break;
            case lambr:
                repository = lambrequinRepository;
                model = (lambrequinRepository.findByName(name)).get(0);
            break;
            case curtFinish:
                repository = curtainFinishRepository;
                model = (curtainFinishRepository.findByName(name)).get(0);
            break;
            case tulleFinish:
                repository = tulleFinishRepository;
                model = (tulleFinishRepository.findByName(name)).get(0);
        }
//        System.out.println("CHECK REPOSITORY: "+repository);
        return model;
    }
}
