package margo.service.cart;

import margo.dao.accessories.*;
import margo.model.finishedProduct.AllFinishProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class CheckAccessoriesRepositoryForCartService {
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

    private final String band = "band";
    private final String fringe = "fringe";
    private final String luvers = "luvers";
    private final String pickup = "pickup";
    private final String various = "various";

    private CrudRepository repository = null;
    private AllFinishProductModel model = null;

    public AllFinishProductModel selectRepository(String part, String name) {
        switch (part) {
            case band:
                repository = bandRepository;
                model = (bandRepository.findByName(name)).get(0);
            break;
            case fringe:
                repository =  fringeRepository;
                model = (fringeRepository.findByName(name)).get(0);
            break;
            case luvers:
                repository =  luversRepository;
                model = (luversRepository.findByName(name)).get(0);
            break;
            case pickup:
                repository =  pickupRepository;
                model = (pickupRepository.findByName(name)).get(0);
            break;
            case various:
                repository =  variousRepository;
                model = (variousRepository.findByName(name)).get(0);
            break;
        }
//        System.out.println("CHECK REPOSITORY: "+repository);
        return model;
    }

    public CrudRepository getRepository() {
        return repository;
    }
}
