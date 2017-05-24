package margo.service.finishedProduct;

import margo.dao.finishProduct.*;
import margo.dao.interior.InteriorRepository;
import margo.model.finishedProduct.AllFinishProductModel;
import margo.model.finishedProduct.BedroomModel;
import margo.model.finishedProduct.GuestroomModel;
import margo.model.modelDTO.allCurtainsDTO.AllFabricDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CheckRepositoryService {
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
    @Autowired
    private MainFinishedService main;

    private final String bedroom = "bedroom";
    private final String cabinet = "cabinet";
    private final String children = "children";
    private final String guestroom = "guestroom";
    private final String kitchen = "kitchen";
    private final String lambr = "lambr";
    private final String curtFinish = "curtFinish";
    private final String tulleFinish = "tulleFinish";

    private final String interior = "interior";


    private CrudRepository repository = null;

    public CrudRepository selectRepository(String part) {
        switch (part) {
            case bedroom:
                repository = bedroomRepository;
            break;
            case cabinet:
                repository =  cabinetRepository;
            break;
            case children:
                repository =  childrenRepository;
            break;
            case guestroom:
                repository =  guestroomRepository;
            break;
            case kitchen:
                repository =  kitchenRepository;
            break;
            case lambr:
                repository =  lambrequinRepository;
            break;
            case curtFinish:
                repository =  curtainFinishRepository;
            break;
            case tulleFinish:
                repository =  tulleFinishRepository;
                break;
//            case interior:
//                repository =  interiorRepository;
//            break;
        }
//        System.out.println("CHECK REPOSITORY: "+repository);
        return repository;
    }
    public List<AllFabricDTO> allInfoInFinishProductsDB(){
        List<AllFabricDTO> list = new ArrayList<>();
        List<AllFabricDTO> allFabricDTOs = main.seeAllModels(bedroomRepository);
        List<AllFabricDTO> allFabricDTOs1 = main.seeAllModels(guestroomRepository);
        List<AllFabricDTO> allFabricDTOs2 = main.seeAllModels(cabinetRepository);
        List<AllFabricDTO> allFabricDTOs3 = main.seeAllModels(childrenRepository);
        List<AllFabricDTO> allFabricDTOs4 = main.seeAllModels(curtainFinishRepository);
        List<AllFabricDTO> allFabricDTOs5 = main.seeAllModels(kitchenRepository);
        List<AllFabricDTO> allFabricDTOs6 = main.seeAllModels(tulleFinishRepository);
        List<AllFabricDTO> allFabricDTOs7 = main.seeAllModels(lambrequinRepository);
        list.addAll(allFabricDTOs);
        list.addAll(allFabricDTOs1);
        list.addAll(allFabricDTOs2);
        list.addAll(allFabricDTOs3);
        list.addAll(allFabricDTOs4);
        list.addAll(allFabricDTOs5);
        list.addAll(allFabricDTOs6);
        list.addAll(allFabricDTOs7);
        return list;
    }
}
