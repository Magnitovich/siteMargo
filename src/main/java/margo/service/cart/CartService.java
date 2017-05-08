package margo.service.cart;

import margo.controller.cart.AllInformationsAboutCustomerController;
import margo.dao.UserRepository;
import margo.dao.cart.CustomerOrderRepository;
import margo.dao.cart.CustomerRepository;
import margo.dao.fabric.*;
import margo.dao.interior.InteriorRepository;
import margo.model.allCurtains.*;
import margo.model.cartOder.CustomerModel;
import margo.model.cartOder.OrderCustomerModel;
import margo.model.finishedProduct.AllFinishProductModel;
import margo.model.interior.InteriorModel;
import margo.model.user.UserModel;
import margo.service.finishedProduct.CheckRepositoryService;
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
    private CheckRepositoryService checkRepositoryService;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerOrderRepository customerOrderRepository;

    @Autowired
    private UserRepository userRepository;

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
    @Autowired
    private InteriorRepository interiorRepository;

    final String clothFabric = "clothFabric";
    final String curtainFabric = "curtain";
    final String orderFabric = "orderCurtain";
    final String tulleFabric = "tulle";
    final String upholsteryFabric = "upholsteryFabric";
    final String interior = "pillow";


    @Transactional
    public void changeInfoInDB(CustomerModel customerModel, final String idFabric, final String photo,
                               final String name, final Double quantityFromUI) {

        Long id = Long.valueOf(idFabric);
        String[] split = photo.split("/");

        if(split[2].contains(clothFabric)) {

            ClothFabricModel model = clothFabricRepository.findOne(id);
            Double quantityInDB = model.getQuantity();

            checkInfo(clothFabricRepository, model, quantityInDB, quantityFromUI);
            saveInformationInCustomerDB(customerModel, model, quantityFromUI);

        } else if (split[2].contains(curtainFabric)){

            CurtainModel model = curtainRepository.findOne(id);
            Double quantityInDB = model.getQuantity();

            checkInfo(curtainRepository, model, quantityInDB, quantityFromUI);
            saveInformationInCustomerDB(customerModel, model, quantityFromUI);

        } else if (split[2].contains(orderFabric)){

            OrderCurtainModel model = orderCurtainRepository.findOne(id);
            Double quantityInDB = model.getQuantity();

            checkInfo(orderCurtainRepository, model, quantityInDB, quantityFromUI);
            saveInformationInCustomerDB(customerModel, model, quantityFromUI);

        } else if (split[2].contains(tulleFabric)){

            TulleModel model = tulleRepository.findOne(id);
            Double quantityInDB = model.getQuantity();

            checkInfo(tulleRepository, model, quantityInDB, quantityFromUI);
            saveInformationInCustomerDB(customerModel, model, quantityFromUI);

        } else if (split[2].contains(upholsteryFabric)){
            List<UpholsteryFabricModel> listModel = upholsteryFabricRepository.findById(id);
            UpholsteryFabricModel model = listModel.get(0);
            Double quantityInDB = model.getQuantity();

            checkInfo(upholsteryFabricRepository, model, quantityInDB, quantityFromUI);
            saveInformationInCustomerDB(customerModel, model, quantityFromUI);

        } else if (split[2].contains(interior)){
            InteriorModel one = interiorRepository.findOne(id);
            Double quantityInDB = one.getQuantity();
            checkInfo(upholsteryFabricRepository, one, quantityInDB, quantityFromUI);
            saveInformationInCustomerDB(customerModel, one, quantityFromUI);

        }
        else {
            CrudRepository crudRepository = checkRepositoryService.selectRepository(split[2]);
            AllFinishProductModel allFinishProductModel = (AllFinishProductModel) crudRepository.findOne(id);
            Double quantityInDB = allFinishProductModel.getQuantity();
            checkInfo(crudRepository, allFinishProductModel, quantityInDB, quantityFromUI);
            saveInformationInCustomerDB(customerModel, allFinishProductModel, quantityFromUI);
        }
    }
    @Transactional
    public void checkInfo(CrudRepository repositories, AllFinishProductModel model, Double quantityInDB, Double quantityFromUI){
        if (quantityInDB == 0) {
            System.out.println("In DB ZERO");
        } else {
            Double result = quantityInDB - quantityFromUI;
            model.setQuantity(result);
            repositories.save(model);
        }
    }
//    @Transactional
//    public void checkInfoFinishProduct(CrudRepository repositories, AllFinishProductModel model, Double quantityInDB, Double quantityFromUI){
//        if (quantityInDB == 0) {
//            System.out.println("In DB ZERO");
//        } else {
//            Double result = quantityInDB - quantityFromUI;
//            model.setQuantity(result);
//            repositories.save(model);
//        }
//    }
    public void saveInformationInCustomerDB(CustomerModel customerModel, AllFinishProductModel model, Double quantityFromUI) {

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
    public UserModel ifUserAuthenticated(String name){

        UserModel one = userRepository.findOne(name);

        return one;
    }
}
