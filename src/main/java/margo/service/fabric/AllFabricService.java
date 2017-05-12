package margo.service.fabric;

import margo.model.modelDTO.allCurtainsDTO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
