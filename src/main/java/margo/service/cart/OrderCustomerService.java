package margo.service.cart;

import margo.dao.cart.CustomerOrderRepository;
import margo.dao.cart.CustomerRepository;
import margo.dao.fabric.*;
import margo.model.allCurtains.*;
import margo.model.cartOder.CustomerModel;
import margo.model.cartOder.OrderCustomerModel;
import margo.model.cartOder.cartDTO.CustomerDTO;
import margo.model.cartOder.cartDTO.OrderCustomerDTO;
import margo.model.modelDTO.allCurtainsDTO.ClothFabricDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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

    final String clothFabric = "clothFabric";
    final String curtainFabric = "curtain";
    final String orderFabric = "orderCurtain";
    final String tulleFabric = "tulle";
    final String upholsteryFabric = "upholsteryFabric";


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

        switch (split[1]){
            case clothFabric:
                List<ClothFabricModel> modelList = clothFabricRepository.findByName(nameModel);
                ClothFabricModel clothFabricModel = modelList.get(0);
                Double result = clothFabricModel.getQuantity()+quantityInDBCustomerModel-quantity;

                clothFabricModel.setQuantity(result);
                clothFabricRepository.save(clothFabricModel);
                break;
            case curtainFabric:
                List<CurtainModel> modelListCurtain = curtainRepository.findByName(nameModel);
                CurtainModel curtainModel = modelListCurtain.get(0);
                Double resultCurtain = curtainModel.getQuantity()+quantityInDBCustomerModel-quantity;
                curtainModel.setQuantity(resultCurtain);
                curtainRepository.save(curtainModel);
                break;
            case orderFabric:
                List<OrderCurtainModel> modelListOrder = orderCurtainRepository.findByName(nameModel);
                OrderCurtainModel orderCurtainModel = modelListOrder.get(0);
                Double resultOrder = orderCurtainModel.getQuantity()+quantityInDBCustomerModel-quantity;
                orderCurtainModel.setQuantity(resultOrder);
                orderCurtainRepository.save(orderCurtainModel);
                break;
            case tulleFabric:
                List<TulleModel> modelListTulle = tulleRepository.findByName(nameModel);
                TulleModel tulleModel = modelListTulle.get(0);
                Double resultTulle = tulleModel.getQuantity()+quantityInDBCustomerModel-quantity;
                tulleModel.setQuantity(resultTulle);
                tulleRepository.save(tulleModel);
                break;
            case upholsteryFabric:
                List<UpholsteryFabricModel> upholsteryFabricModels = upholsteryFabricRepository.findByName(nameModel);
                UpholsteryFabricModel upholsteryFabricModel = upholsteryFabricModels.get(0);
                Double resultUph = upholsteryFabricModel.getQuantity()+quantityInDBCustomerModel-quantity;
                upholsteryFabricModel.setQuantity(resultUph);
                upholsteryFabricRepository.save(upholsteryFabricModel);
                break;
        }
    }

}
