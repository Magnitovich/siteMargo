package margo.service.offer;

import margo.dao.accessories.*;
import margo.dao.fabric.*;
import margo.dao.finishProduct.*;
import margo.dao.interior.InteriorRepository;
import margo.model.modelDTO.allCurtainsDTO.AllFabricDTO;
import margo.service.accessories.AccessoriesService;
import margo.service.finishedProduct.MainFinishedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SelectRepositoryService {
    @Autowired
    private ClothFabricRepository clothFabricRepository;
    @Autowired
    private CurtainRepository curtainRepository;
    @Autowired
    private OrderCurtainRepository orderCurtainRepository;
    @Autowired
    private TulleRepository tulleRepository;
    @Autowired
    private UpholsteryFabricRepository upholsteryFabricRepository;
    @Autowired
    private InteriorRepository interiorRepository;

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
    private BandRepository bandRepository;
    @Autowired
    private FringeRepository fringeRepository;
    @Autowired
    private LuversRepository luversRepository;
    @Autowired
    private PickupRepository pickupRepository;
    @Autowired
    private VariousRepository variousRepository;

    @Autowired
    private MainFinishedService main;

    private final String band = "band";
    private final String fringe = "fringe";
    private final String luvers = "luvers";
    private final String pickup = "pickup";
    private final String various = "various";

    private final String bedroom = "bedroom";
    private final String cabinet = "cabinet";
    private final String children = "children";
    private final String guestroom = "guestroom";
    private final String kitchen = "kitchen";
    private final String lambr = "lambr";
    private final String curtFinish = "curtFinish";
    private final String tulleFinish = "tulleFinish";

    final String clothFabric = "clothFabric";
    final String curtainFabric = "curtain";
    final String orderFabric = "orderCurtain";
    final String tulleFabric = "tulle";
    final String upholsteryFabric = "upholsteryFabric";

    final String interior = "pillow";

    private CrudRepository repository = null;

    public CrudRepository selectRepository(String part) {
        switch (part) {
            case clothFabric:
                repository = clothFabricRepository;
                break;
            case upholsteryFabric:
                repository = upholsteryFabricRepository;
                break;
            case orderFabric:
                repository =  orderCurtainRepository;
                break;
            case curtainFabric:
                repository =  curtainRepository;
                break;
            case tulleFabric:
                repository =  tulleRepository;
                break;
            case band:
                repository = bandRepository;
                break;
            case fringe:
                repository =  fringeRepository;
                break;
            case luvers:
                repository =  luversRepository;
                break;
            case pickup:
                repository =  pickupRepository;
                break;
            case various:
                repository =  variousRepository;
                break;
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
            case interior:
                repository =  interiorRepository;
            break;
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
    public List<AllFabricDTO> allInfoAboutFabricDB(){
        List<AllFabricDTO> list = new ArrayList<>();
        List<AllFabricDTO> allFabricDTOs = main.seeAllModels(clothFabricRepository);
        List<AllFabricDTO> allFabricDTOs1 = main.seeAllModels(curtainRepository);
        List<AllFabricDTO> allFabricDTOs2 = main.seeAllModels(tulleRepository);
        List<AllFabricDTO> allFabricDTOs3 = main.seeAllModels(orderCurtainRepository);
        List<AllFabricDTO> allFabricDTOs4 = main.seeAllModels(upholsteryFabricRepository);
        list.addAll(allFabricDTOs);
        list.addAll(allFabricDTOs1);
        list.addAll(allFabricDTOs2);
        list.addAll(allFabricDTOs3);
        list.addAll(allFabricDTOs4);
        return list;
    }

}
