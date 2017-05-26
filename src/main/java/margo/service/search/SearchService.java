package margo.service.search;

import margo.dao.accessories.*;
import margo.dao.fabric.TulleRepository;
import margo.dao.finishProduct.*;
import margo.dao.interior.InteriorRepository;
import margo.dao.serviceMargo.ServiceMargoRepository;
import margo.model.modelDTO.allCurtainsDTO.*;
import margo.service.accessories.CheckAccessoriesRepositoryService;
import margo.service.fabric.*;
import margo.service.interior.InteriorService;
import margo.service.offer.SelectRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {

    //FINISH PRODUCT
    @Autowired
    private SelectRepositoryService finishedService;
    //ACCESSORIES
    @Autowired
    private CheckAccessoriesRepositoryService accessoriesRepositoryService;

    //INTERIOR
    @Autowired
    private InteriorService interiorService;
    //SERVICE_MARGO
    @Autowired
    private ServiceMargoRepository serviceMargoRepository;

    private List<AllFabricDTO> allFabricDTOs = new ArrayList<>();

    public List<AllFabricDTO> searchAnswer(String search){

        allFabricDTOs.clear();
        List<AllFabricDTO> listAccessories = accessoriesRepositoryService.seeAllAccessories();
        for (AllFabricDTO allFabricDTO: listAccessories){
            if (allFabricDTO.getName().toLowerCase().contains(search)
                    ||allFabricDTO.getName().toUpperCase().contains(search)) {
                this.allFabricDTOs.add(allFabricDTO);
            }
        }
        for (AllFabricDTO dtoFinish:interiorService.seeAllInteriorForSearch()){
            if (dtoFinish.getName().toLowerCase().contains(search)
                    ||dtoFinish.getName().toUpperCase().contains(search)) {
                this.allFabricDTOs.add(dtoFinish);
            }
        }

        List<AllFabricDTO> allFinishFabricDTOs = finishedService.allInfoInFinishProductsDB();
//        System.out.println("Search service");
//        System.out.println(allFabricDTOs.toString());
        for (AllFabricDTO dtoFinish:allFinishFabricDTOs){
            if (dtoFinish.getName().toLowerCase().contains(search)
                    ||dtoFinish.getName().toUpperCase().contains(search)) {
                this.allFabricDTOs.add(dtoFinish);
            }
        }

        List<AllFabricDTO> allFabricDTOs = finishedService.allInfoAboutFabricDB();
//        List<AllFabricDTO> searchCloth = new ArrayList<>();
//        searchCloth.addAll(allFabricDTOs);
        for(AllFabricDTO clothFabricDTO : allFabricDTOs){
            if (clothFabricDTO.getName().toLowerCase().contains(search)
                    ||clothFabricDTO.getName().toUpperCase().contains(search)) {
                this.allFabricDTOs.add(clothFabricDTO);
            }
        }
//        List<CurtainDTO> curtainDTOs = curtainService.seeAllModels();
//        List<CurtainDTO> searchCurtains = new ArrayList<>();
//        searchCurtains.addAll(curtainDTOs);
//        for(CurtainDTO curtainDTO : searchCurtains){
//            if (curtainDTO.getName().toLowerCase().contains(search)
//                    ||curtainDTO.getName().toUpperCase().contains(search)) {
//                this.allFabricDTOs.add(curtainDTO);
//            }
//        }
//        List<OrderCurtainDTO> orderCurtainDTOs = orderCurtainService.seeAllModels();
//        List<OrderCurtainDTO> searchOrder = new ArrayList<>();
//        searchOrder.addAll(orderCurtainDTOs);
//        for(OrderCurtainDTO orderCurtainDTO : searchOrder){
//            if (orderCurtainDTO.getName().toLowerCase().contains(search)
//                    ||orderCurtainDTO.getName().toUpperCase().contains(search)) {
//                this.allFabricDTOs.add(orderCurtainDTO);
//            }
//        }
//        List<TulleDTO> tulleDTOs = tulleService.seeAllModels();
//        List<TulleDTO> searchTulle = new ArrayList<>();
//        searchTulle.addAll(tulleDTOs);
//        for(TulleDTO tulleDTO : searchTulle){
//            if (tulleDTO.getName().toLowerCase().contains(search)
//                    ||tulleDTO.getName().toUpperCase().contains(search)) {
//                this.allFabricDTOs.add(tulleDTO);
//            }
//        }
//        List<UpholsteryFabricDTO> upholsteryFabricDTOs = upholsteryFabricService.seeAllModels();
//        List<UpholsteryFabricDTO> searchUpholster = new ArrayList<>();
//        searchUpholster.addAll(upholsteryFabricDTOs);
//        for(UpholsteryFabricDTO upholsteryFabricDTO : searchUpholster){
//            if (upholsteryFabricDTO.getName().toLowerCase().contains(search)
//                    ||upholsteryFabricDTO.getName().toUpperCase().contains(search)) {
//                this.allFabricDTOs.add(upholsteryFabricDTO);
//            }
//        }
//        List<AllFabricDTO> allFabricDTOs = finishedService.seeAllModels(bedroomRepository);
//        List<AllFabricDTO> searchBedroom = new ArrayList<>();
//        searchBedroom.addAll(allBedroom);
//        for(ClothFabricDTO clothFabricDTO : searchCloth){
//            if (clothFabricDTO.getName().toLowerCase().contains(search)
//                    ||clothFabricDTO.getName().toUpperCase().contains(search)) {
//                this.allFabricDTOs.add(clothFabricDTO);
//            }
//        }
//        List<CurtainDTO> curtainDTOs = curtainService.seeAllModels();
//        List<CurtainDTO> searchCurtains = new ArrayList<>();
//        searchCurtains.addAll(curtainDTOs);
//        for(CurtainDTO curtainDTO : searchCurtains){
//            if (curtainDTO.getName().toLowerCase().contains(search)
//                    ||curtainDTO.getName().toUpperCase().contains(search)) {
//                this.allFabricDTOs.add(curtainDTO);
//            }
//        }
//        List<OrderCurtainDTO> orderCurtainDTOs = orderCurtainService.seeAllModels();
//        List<OrderCurtainDTO> searchOrder = new ArrayList<>();
//        searchOrder.addAll(orderCurtainDTOs);
//        for(OrderCurtainDTO orderCurtainDTO : searchOrder){
//            if (orderCurtainDTO.getName().toLowerCase().contains(search)
//                    ||orderCurtainDTO.getName().toUpperCase().contains(search)) {
//                this.allFabricDTOs.add(orderCurtainDTO);
//            }
//        }
//        List<TulleDTO> tulleDTOs = tulleService.seeAllModels();
//        List<TulleDTO> searchTulle = new ArrayList<>();
//        searchTulle.addAll(tulleDTOs);
//        for(TulleDTO tulleDTO : searchTulle){
//            if (tulleDTO.getName().toLowerCase().contains(search)
//                    ||tulleDTO.getName().toUpperCase().contains(search)) {
//                this.allFabricDTOs.add(tulleDTO);
//            }
//        }
//        List<UpholsteryFabricDTO> upholsteryFabricDTOs = upholsteryFabricService.seeAllModels();
//        List<UpholsteryFabricDTO> searchUpholster = new ArrayList<>();
//        searchUpholster.addAll(upholsteryFabricDTOs);
//        for(UpholsteryFabricDTO upholsteryFabricDTO : searchUpholster){
//            if (upholsteryFabricDTO.getName().toLowerCase().contains(search)
//                    ||upholsteryFabricDTO.getName().toUpperCase().contains(search)) {
//                this.allFabricDTOs.add(upholsteryFabricDTO);
//            }
//        }

        return this.allFabricDTOs;
        }
    }


