package margo.service.offer;

import margo.model.modelDTO.allCurtainsDTO.AllFabricDTO;
import margo.service.accessories.CheckAccessoriesRepositoryService;
import margo.service.fabric.*;
import margo.service.interior.InteriorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class MainOfferService {


    @Autowired
    private CheckAccessoriesRepositoryService accessoriesService;
    @Autowired
    private InteriorService interiorService;
    @Autowired
    private SelectRepositoryService finishService;



    private List<AllFabricDTO> mainList = new ArrayList<>();



    public List<AllFabricDTO> showAll(Double percent, Integer quantity){
        List<AllFabricDTO> forOffer = new ArrayList<>();

//        System.out.println("LIST before shuffle: "+listAccessories);
        Collections.shuffle(mainList);
        System.out.println();
//        System.out.println("LIST after: "+listAccessories);
        System.out.println();
        System.out.println("first get(0): "+mainList.get(0));
        System.out.println();
        System.out.println("get(1): "+mainList.get(1));

        for (int i = 0; i<quantity; i++){
            forOffer.add(mainList.get(i));
        }
        System.out.println();
        System.out.println("Size: "+forOffer);
        return forOffer;
    }
}
