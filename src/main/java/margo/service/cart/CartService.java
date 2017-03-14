package margo.service.cart;

import margo.controller.cart.AllInformationsAboutCustomerController;
import margo.dao.cart.CustomerOrderRepository;
import margo.dao.cart.CustomerRepository;
import margo.dao.fabric.*;
import margo.model.allCurtains.*;
import margo.model.cartOder.CustomerModel;
import margo.model.cartOder.OrderCustomerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private AllInformationsAboutCustomerController customerController;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerOrderRepository customerOrderRepository;

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

    List<AllFabricModel> fabricModels = new ArrayList<>();

    final String clothFabric = "clothFabric";
    final String curtainFabric = "curtain";
    final String orderFabric = "orderCurtain";
    final String tulleFabric = "tulle";
    final String upholsteryFabric = "upholsteryFabric";

    @Transactional
    public void changeInfoInDB(CustomerModel customerModel, final String idFabric, final String photo,
                               final String name, final Double quantityFromUI) {

        Long id = Long.valueOf(idFabric);
        String[] split = photo.split("/");

       if(split[1].contains(clothFabric)) {

           List<ClothFabricModel> clothFabricModels = clothFabricRepository.findById(id);
            ClothFabricModel model = clothFabricModels.get(0);
            ClothFabricModel modelToSend = clothFabricModels.get(0);
           Double quantityInDB = model.getQuantity();

           checkInfo(clothFabricRepository, modelToSend, quantityInDB, quantityFromUI);

//           model.getPhoto(); model.getName(); model.getDescription(); model.getStructure();
//           model.getPrice(); model.getPaint(); model.getHeight(); model.getColor();            model.setQuantity(quantityFromUI);
//           fabricModels.add(model);
//
           saveInformationInCustomerDB(customerModel, model, quantityFromUI);

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
           saveInformationInCustomerDB(customerModel, model, quantityFromUI);

       } else if (split[1].contains(orderFabric)){
           List<OrderCurtainModel> listModel = orderCurtainRepository.findById(id);
           OrderCurtainModel model = listModel.get(0);
           Double quantityInDB = model.getQuantity();

           checkInfo(orderCurtainRepository, model, quantityInDB, quantityFromUI);
           saveInformationInCustomerDB(customerModel, model, quantityFromUI);

       } else if (split[1].contains(tulleFabric)){
           List<TulleModel> listModel = tulleRepository.findById(id);
           TulleModel model = listModel.get(0);
           Double quantityInDB = model.getQuantity();

           checkInfo(tulleRepository, model, quantityInDB, quantityFromUI);
           saveInformationInCustomerDB(customerModel, model, quantityFromUI);

       } else if (split[1].contains(upholsteryFabric)){
           List<UpholsteryFabricModel> listModel = upholsteryFabricRepository.findById(id);
           UpholsteryFabricModel model = listModel.get(0);
           Double quantityInDB = model.getQuantity();

           checkInfo(upholsteryFabricRepository, model, quantityInDB, quantityFromUI);
           saveInformationInCustomerDB(customerModel, model, quantityFromUI);
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
    public void saveInformationInCustomerDB(CustomerModel customerModel, AllFabricModel model, Double quantityFromUI) {

        OrderCustomerModel orderCustomerModel = new OrderCustomerModel();

        orderCustomerModel.setPhoto(model.getPhoto());
        orderCustomerModel.setName(model.getName());
        orderCustomerModel.setDescription(model.getDescription());
        orderCustomerModel.setStructure(model.getStructure());
        orderCustomerModel.setPaint(model.getPaint());
        orderCustomerModel.setHeight(model.getHeight());
        orderCustomerModel.setColor(model.getColor());
        orderCustomerModel.setQuantity(quantityFromUI);
        orderCustomerModel.setPrice(model.getPrice());

        orderCustomerModel.setCustomerOrder(customerModel);
//        System.out.println("MODEL: PHOTO: "+model.getPhoto()+ "NAME: "+model.getName()+" DESCR: "+model.getDescription()+
//        " PRICE: "+model.getPrice()+" QUANTITY: "+quantityFromUI);
        customerRepository.save(customerModel);
        customerOrderRepository.save(orderCustomerModel);

    }
}
