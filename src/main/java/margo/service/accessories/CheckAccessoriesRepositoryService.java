package margo.service.accessories;

import margo.dao.accessories.*;
import margo.dao.finishProduct.*;
import margo.dao.interior.InteriorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

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
}
