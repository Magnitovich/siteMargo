package margo.service.cart;

import margo.dao.fabric.*;
import margo.model.allCurtains.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public void changeInfoInDB(final String idFabric, final String photo,
                               final String name, final Double quantityFromUI) {

        Long id = Long.valueOf(idFabric);

        final String clothFabric = "clothFabric";
        final String curtainFabric = "curtain";
        final String orderFabric = "orderCurtain";
        final String tulleFabric = "tulle";
        final String upholsteryFabric = "upholsteryFabric";

        String[] split = photo.split("/");
//        System.out.println("SPLIT[0]  "+split[0]+"  SPLIT[1]"+ split[1]);

       if(split[1].contains(clothFabric)) {

           List<ClothFabricModel> clothFabricModels = clothFabricRepository.findById(id);
            ClothFabricModel model = clothFabricModels.get(0);
           Double quantityInDB = model.getQuantity();

           checkInfo(clothFabricRepository, model, quantityInDB, quantityFromUI);

//           if (quantityInDB == 0) {
//               System.out.println("In DB ZERO");
//           } else {
//
//               Double result = quantityInDB - quantityFromUI;
//               model.setQuantity(result);
//               clothFabricRepository.save(model);
//           }
       } else if (split[1].contains(curtainFabric)){

           List<CurtainModel> listModel = curtainRepository.findById(id);
           CurtainModel model = listModel.get(0);
           Double quantityInDB = model.getQuantity();

           checkInfo(curtainRepository, model, quantityInDB, quantityFromUI);

       } else if (split[1].contains(orderFabric)){
           List<OrderCurtainModel> listModel = orderCurtainRepository.findById(id);
           OrderCurtainModel model = listModel.get(0);
           Double quantityInDB = model.getQuantity();

           checkInfo(orderCurtainRepository, model, quantityInDB, quantityFromUI);

       } else if (split[1].contains(tulleFabric)){
           List<TulleModel> listModel = tulleRepository.findById(id);
           TulleModel model = listModel.get(0);
           Double quantityInDB = model.getQuantity();

           checkInfo(tulleRepository, model, quantityInDB, quantityFromUI);

       } else if (split[1].contains(upholsteryFabric)){
           List<UpholsteryFabricModel> listModel = upholsteryFabricRepository.findById(id);
           UpholsteryFabricModel model = listModel.get(0);
           Double quantityInDB = model.getQuantity();

           checkInfo(upholsteryFabricRepository, model, quantityInDB, quantityFromUI);

       }

        }

    public void checkInfo(CrudRepository repositories, AllFabricModel model, Double quantityInDB, Double quantityFromUI){
        if (quantityInDB == 0) {
            System.out.println("In DB ZERO");
        } else {

            Double result = quantityInDB - quantityFromUI;
            model.setQuantity(result);
            repositories.save(model);
        }
    }

}
