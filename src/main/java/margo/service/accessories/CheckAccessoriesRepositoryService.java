package margo.service.accessories;

import margo.dao.accessories.*;
import margo.dao.finishProduct.*;
import margo.dao.interior.InteriorRepository;
import margo.model.modelDTO.accessories.AccessoriesDTO;
import margo.model.modelDTO.allCurtainsDTO.AllFabricDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.Lifecycle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CheckAccessoriesRepositoryService {
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
    private AccessoriesService service;

    private final String band = "band";
    private final String fringe = "fringe";
    private final String luvers = "luvers";
    private final String pickup = "pickup";
    private final String various = "various";

    private CrudRepository repository = null;

    public CrudRepository selectRepository(String part) {
        switch (part) {
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
        }
//        System.out.println("CHECK REPOSITORY: "+repository);
        return repository;
    }
    public List<AllFabricDTO> seeAllAccessories(){
        List<AllFabricDTO> list = new ArrayList<>();
        List<AllFabricDTO> accessoriesDTOs = service.seeAllModelsToSearch(variousRepository);
        List<AllFabricDTO> accessoriesDTOs1 = service.seeAllModelsToSearch(bandRepository);
        List<AllFabricDTO> accessoriesDTOs2 = service.seeAllModelsToSearch(fringeRepository);
        List<AllFabricDTO> accessoriesDTOs3 = service.seeAllModelsToSearch(luversRepository);
        List<AllFabricDTO> accessoriesDTOs4 = service.seeAllModelsToSearch(pickupRepository);
        list.addAll(accessoriesDTOs);
        list.addAll(accessoriesDTOs1);
        list.addAll(accessoriesDTOs2);
        list.addAll(accessoriesDTOs3);
        list.addAll(accessoriesDTOs4);
        return list;
    }
}
