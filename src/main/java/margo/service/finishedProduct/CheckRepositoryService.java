//package margo.service.finishedProduct;
//
//import margo.dao.finishProduct.*;
//import margo.dao.interior.InteriorRepository;
//import margo.model.finishedProduct.AllFinishProductModel;
//import margo.model.finishedProduct.BedroomModel;
//import margo.model.finishedProduct.GuestroomModel;
//import margo.model.modelDTO.allCurtainsDTO.AllFabricDTO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class CheckRepositoryService {
//    @Autowired
//    private BedroomRepository bedroomRepository;
//    @Autowired
//    private CabinetRepository cabinetRepository;
//    @Autowired
//    private ChildrenRepository childrenRepository;
//    @Autowired
//    private CurtainFinishRepository curtainFinishRepository;
//    @Autowired
//    private GuestroomRepository guestroomRepository;
//    @Autowired
//    private KitchenRepository kitchenRepository;
//    @Autowired
//    private LambrequinRepository lambrequinRepository;
//    @Autowired
//    private TulleFinishRepository tulleFinishRepository;
//
//
//    private final String bedroom = "bedroom";
//    private final String cabinet = "cabinet";
//    private final String children = "children";
//    private final String guestroom = "guestroom";
//    private final String kitchen = "kitchen";
//    private final String lambr = "lambr";
//    private final String curtFinish = "curtFinish";
//    private final String tulleFinish = "tulleFinish";
//
//    private final String interior = "interior";
//
//
//    private CrudRepository repository = null;
//
//    public CrudRepository selectRepository(String part) {
//        switch (part) {
//            case bedroom:
//                repository = bedroomRepository;
//            break;
//            case cabinet:
//                repository =  cabinetRepository;
//            break;
//            case children:
//                repository =  childrenRepository;
//            break;
//            case guestroom:
//                repository =  guestroomRepository;
//            break;
//            case kitchen:
//                repository =  kitchenRepository;
//            break;
//            case lambr:
//                repository =  lambrequinRepository;
//            break;
//            case curtFinish:
//                repository =  curtainFinishRepository;
//            break;
//            case tulleFinish:
//                repository =  tulleFinishRepository;
//                break;
////            case interior:
////                repository =  interiorRepository;
////            break;
//        }
////        System.out.println("CHECK REPOSITORY: "+repository);
//        return repository;
//    }
//
//}
