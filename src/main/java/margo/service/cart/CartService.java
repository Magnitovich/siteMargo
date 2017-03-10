package margo.service.cart;

import margo.dao.fabric.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartService {

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

    @Transactional
    public void changeInfoInDB(final String id, final String photo,
                               final String name, final Integer quantityFromUI) {

        String clothFabric = "clothFabric";
        String curtainFabric = "curtain";
        String orderFabric = "orderCurtain";
        String tulleFabric = "tulle";
        String upholsteryFabric = "upholsteryFabric";

        String[] split = photo.split("/");
        System.out.println("SPLIT[0]  "+split[0]+"  SPLIT[1]"+ split[1]);



    }

}
