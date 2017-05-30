package margo.service.cart;

import margo.dao.cart.CustomerOrderRepository;
import margo.dao.fabric.*;
import margo.dao.interior.InteriorRepository;
import margo.dao.serviceMargo.ServiceMargoRepository;
import margo.model.allCurtains.*;
import margo.model.cartOder.OrderCustomerModel;
import margo.model.cartOder.cartDTO.OrderCustomerDTO;
import margo.model.finishedProduct.AllFinishProductModel;
import margo.model.interior.InteriorModel;
import margo.model.serviceMargo.ServiceMargoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderCustomerService {

    @Autowired
    private CustomerOrderRepository orderRepository;

    String photo = null;
    String nameModel = null;

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
    @Autowired
    private ServiceMargoRepository serviceMargoRepository;
    @Autowired
    private CheckFinishProductRepositoryService checkRepositoryService;
    @Autowired
    private CheckAccessoriesRepositoryForCartService checkAccessoriesRepositoryService;

    final String clothFabric = "clothFabric";
    final String curtainFabric = "curtain";
    final String orderFabric = "orderCurtain";
    final String tulleFabric = "tulle";
    final String upholsteryFabric = "upholsteryFabric";
    final String interior = "pillow";
    final String accessories = "accessories";
    final String finishProduct = "finishProduct";
    final String serviceMargo = "serviceMargo";


    public OrderCustomerDTO convertModelToDto(OrderCustomerModel model){

        OrderCustomerDTO orderCustomerDTO = new OrderCustomerDTO();
        orderCustomerDTO.setId(model.getId());
        orderCustomerDTO.setPhoto(model.getPhoto());
        orderCustomerDTO.setName(model.getName());
        orderCustomerDTO.setDescription(model.getDescription());
        orderCustomerDTO.setStructure(model.getStructure());
        orderCustomerDTO.setPaint(model.getPaint());
        orderCustomerDTO.setHeight(model.getHeight());
        orderCustomerDTO.setColor(model.getColor());
        orderCustomerDTO.setQuantity(model.getQuantity());
        orderCustomerDTO.setPrice(model.getPrice());

        return orderCustomerDTO;
    }

    public List<OrderCustomerDTO> convertListModelToDTO(List<OrderCustomerModel> modelList){

        List<OrderCustomerDTO> customerDTOs = new ArrayList<>();
        for(OrderCustomerModel model: modelList){
            OrderCustomerDTO dto = convertModelToDto(model);
            customerDTOs.add(dto);
        }
        return customerDTOs;
    }
    public List<OrderCustomerDTO> showAllCustomer(){
        Iterable<OrderCustomerModel> customerModels = orderRepository.findAll();
        List<OrderCustomerDTO> customerDTOs = convertListModelToDTO((List<OrderCustomerModel>) customerModels);
        return customerDTOs;
    }
    public List<OrderCustomerDTO> seeSelectedCustomer(Long id){
        List<OrderCustomerModel> customerModel = orderRepository.findOrderCustomer(id);
        List<OrderCustomerDTO> customerDTO = convertListModelToDTO(customerModel);
        return customerDTO;

    }

    public  void changeOrder(Long id, Double quantity){

        OrderCustomerModel one = orderRepository.findOne(id);
        Double quantityInDBCustomerModel = one.getQuantity();
        BigDecimal price = one.getPrice();
        BigDecimal quntityInBigDecimal = BigDecimal.valueOf(quantityInDBCustomerModel);
        BigDecimal newQuantity = BigDecimal.valueOf(quantity);
        BigDecimal firstPrice = price.divide(quntityInBigDecimal);
        BigDecimal newPrice = firstPrice.multiply(newQuantity);
        one.setQuantity(quantity);
        one.setPrice(newPrice);
        photo = one.getPhoto();
        nameModel = one.getName();
        orderRepository.save(one);
//        System.out.println("***********OrderCustomerService****************");
//        System.out.println("Past Quantity in DB: "+quantityInDBCustomerModel);
//        System.out.println("Photo: "+photo+" Name: "+nameModel);
        String[] split = photo.split("/");

            if(split[2].contains(clothFabric)) {
                List<ClothFabricModel> byName = clothFabricRepository.findByName(nameModel);
                chengeInfoInQuantity(clothFabricRepository, byName.get(0), quantityInDBCustomerModel, quantity);

        } else if(split[2].contains(curtainFabric)) {
                List<CurtainModel> byName = curtainRepository.findByName(nameModel);
                chengeInfoInQuantity(curtainRepository, byName.get(0), quantityInDBCustomerModel, quantity);

        } else if(split[2].contains(orderFabric)) {
                List<OrderCurtainModel> byName = orderCurtainRepository.findByName(nameModel);
                chengeInfoInQuantity(orderCurtainRepository, byName.get(0), quantityInDBCustomerModel, quantity);

        }else if(split[2].contains(tulleFabric)) {
                List<TulleModel> byName = tulleRepository.findByName(nameModel);
                chengeInfoInQuantity(tulleRepository, byName.get(0), quantityInDBCustomerModel, quantity);

        }else if(split[2].contains(upholsteryFabric)) {
                List<UpholsteryFabricModel> byName = upholsteryFabricRepository.findByName(nameModel);
                chengeInfoInQuantity(upholsteryFabricRepository, byName.get(0), quantityInDBCustomerModel, quantity);

        }else if(split[2].contains(interior)) {
                List<InteriorModel> byName = interiorRepository.findByName(nameModel);
                chengeInfoInQuantity(interiorRepository, byName.get(0), quantityInDBCustomerModel, quantity);

        }else if(split[1].contains(serviceMargo)) {
                List<ServiceMargoModel> byName = serviceMargoRepository.findByName(nameModel);
                chengeInfoInQuantity(serviceMargoRepository, byName.get(0), quantityInDBCustomerModel, quantity);

        }else if(split[1].contains(finishProduct)) {
                AllFinishProductModel model1 = checkRepositoryService.selectRepository(split[2], nameModel);
                CrudRepository repository = checkRepositoryService.getRepository();
                chengeInfoInQuantity(repository, model1, quantityInDBCustomerModel, quantity);

        }else if(split[1].contains(accessories)) {
                AllFinishProductModel model = checkAccessoriesRepositoryService.selectRepository(split[2], nameModel);
                CrudRepository repository = checkAccessoriesRepositoryService.getRepository();
                chengeInfoInQuantity(repository, model, quantityInDBCustomerModel, quantity);

        }

        }
    @Transactional
    public void chengeInfoInQuantity(CrudRepository repository, AllFinishProductModel model,
                                     Double quantityInDBCustomerModel, Double quantityFromUI){

        Double result = model.getQuantity() + quantityInDBCustomerModel - quantityFromUI;
        model.setQuantity(result);
        repository.save(model);
    }

}
