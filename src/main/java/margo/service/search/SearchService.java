package margo.service.search;

import margo.model.modelDTO.allCurtainsDTO.*;
import margo.service.fabric.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {
    @Autowired
    private ClothFabricService clothFabricService;
    @Autowired
    private CurtainService curtainService;
    @Autowired
    private TulleService tulleService;
    @Autowired
    private UpholsteryFabricService upholsteryFabricService;
    @Autowired
    private OrderCurtainService orderCurtainService;

    private List<AllFabricDTO> allFabricDTOs = new ArrayList<>();

    public List<AllFabricDTO> searchAnswer(String search){

        allFabricDTOs.clear();

        List<ClothFabricDTO> clothFabricModels = clothFabricService.seeAllCloth();
        List<ClothFabricDTO> searchCloth = new ArrayList<>();
        searchCloth.addAll(clothFabricModels);
        for(ClothFabricDTO clothFabricDTO : searchCloth){
            if (clothFabricDTO.getName().toLowerCase().contains(search)
                    ||clothFabricDTO.getName().toUpperCase().contains(search)) {
                allFabricDTOs.add(clothFabricDTO);
            }
        }
        List<CurtainDTO> curtainDTOs = curtainService.seeAllModels();
        List<CurtainDTO> searchCurtains = new ArrayList<>();
        searchCurtains.addAll(curtainDTOs);
        for(CurtainDTO curtainDTO : searchCurtains){
            if (curtainDTO.getName().toLowerCase().contains(search)
                    ||curtainDTO.getName().toUpperCase().contains(search)) {
                allFabricDTOs.add(curtainDTO);
            }
        }
        List<OrderCurtainDTO> orderCurtainDTOs = orderCurtainService.seeAllModels();
        List<OrderCurtainDTO> searchOrder = new ArrayList<>();
        searchOrder.addAll(orderCurtainDTOs);
        for(OrderCurtainDTO orderCurtainDTO : searchOrder){
            if (orderCurtainDTO.getName().toLowerCase().contains(search)
                    ||orderCurtainDTO.getName().toUpperCase().contains(search)) {
                allFabricDTOs.add(orderCurtainDTO);
            }
        }
        List<TulleDTO> tulleDTOs = tulleService.seeAllModels();
        List<TulleDTO> searchTulle = new ArrayList<>();
        searchTulle.addAll(tulleDTOs);
        for(TulleDTO tulleDTO : searchTulle){
            if (tulleDTO.getName().toLowerCase().contains(search)
                    ||tulleDTO.getName().toUpperCase().contains(search)) {
                allFabricDTOs.add(tulleDTO);
            }
        }
        List<UpholsteryFabricDTO> upholsteryFabricDTOs = upholsteryFabricService.seeAllModels();
        List<UpholsteryFabricDTO> searchUpholster = new ArrayList<>();
        searchUpholster.addAll(upholsteryFabricDTOs);
        for(UpholsteryFabricDTO upholsteryFabricDTO : searchUpholster){
            if (upholsteryFabricDTO.getName().toLowerCase().contains(search)
                    ||upholsteryFabricDTO.getName().toUpperCase().contains(search)) {
                allFabricDTOs.add(upholsteryFabricDTO);
            }
        }

        return allFabricDTOs;
        }
    }

