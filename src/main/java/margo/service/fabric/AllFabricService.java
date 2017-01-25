package margo.service.fabric;

import margo.dao.fabric.ClothFabricRepository;
import margo.dao.fabric.CurtainRepository;
import margo.model.allCurtains.ClothFabricModel;
import margo.model.modelDTO.allCurtainsDTO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AllFabricService {
    @Autowired
    private ClothFabricService clothFabricService;
    @Autowired
    private CurtainService curtainService;
    @Autowired
    private OrderCurtainService orderCurtainService;
    @Autowired
    private TulleService tulleService;
    @Autowired
    private UpholsteryFabricService upholsteryFabricService;

    @Autowired
    private ClothFabricRepository clothFabricRepository;
    @Autowired
    private CurtainRepository curtainRepository;

//    public List<AllFabricDTO> seeAllFabric(){
//
//        List<AllFabricDTO> allFabricDTOs = new ArrayList<>();
//
//        Iterable<ClothFabricModel> clothFabricDTOs = clothFabricRepository.findAll();
//        List<CurtainDTO> curtainDTOs = curtainService.seeAllModels();
////        List<OrderCurtainDTO> orderCurtainDTOs = orderCurtainService.seeAllModels();
////        List<TulleDTO> tulleDTOs = tulleService.seeAllModels();
////        List<UpholsteryFabricDTO> upholsteryFabricDTOs = upholsteryFabricService.seeAllModels();
//
//        allFabricDTOs.add((AllFabricDTO) clothFabricDTOs);
//        allFabricDTOs.add((AllFabricDTO) curtainDTOs);
////        allFabricDTOs.add((AllFabricDTO) orderCurtainDTOs);
////        allFabricDTOs.add((AllFabricDTO) tulleDTOs);
////        allFabricDTOs.add((AllFabricDTO) upholsteryFabricDTOs);
//
//        return allFabricDTOs;
//    }

    public List<ClothFabricDTO> seeClothFabric(){
        List<ClothFabricDTO> clothFabricDTOs = clothFabricService.seeAllCloth();
        return clothFabricDTOs;
    }
    public List<CurtainDTO> seeCurtain(){
        List<CurtainDTO> curtainDTOs = curtainService.seeAllModels();
        return curtainDTOs;
    }
    public List<OrderCurtainDTO> seeOrderCurtain(){
        List<OrderCurtainDTO> orderCurtainDTOs = orderCurtainService.seeAllModels();
        return orderCurtainDTOs;
    }
    public List<TulleDTO> seeTulle(){
        List<TulleDTO> tulleDTOs = tulleService.seeAllModels();
        return tulleDTOs;
    }
    public List<UpholsteryFabricDTO> seeUpholsteryFabric(){
        List<UpholsteryFabricDTO> upholsteryFabricDTOs = upholsteryFabricService.seeAllModels();
        return upholsteryFabricDTOs;
    }



}
