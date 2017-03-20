package margo.service.cart;

import margo.dao.cart.CustomerOrderRepository;
import margo.dao.cart.CustomerRepository;
import margo.model.cartOder.CustomerModel;
import margo.model.cartOder.OrderCustomerModel;
import margo.model.cartOder.cartDTO.CustomerDTO;
import margo.model.cartOder.cartDTO.OrderCustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderCustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerOrderRepository orderRepository;

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

//    public void addInfoAboutCustomerOrder(final String nameCustomer, final String emailCustomer, final String phoneCustomer,
//                                          final String addressCustomer,
//                                          final String photo, final String nameFabric, final String description,
//                                          final String structure, final String paint, final String height,
//                                          final String color, final Double quantity, final BigDecimal price){
//
//        Date date = new Date();
//        CustomerModel customerModel = new CustomerModel();
//        OrderCustomerModel orderCustomerModel = new OrderCustomerModel();
//        customerModel.setNameCustomer(nameCustomer);
//        customerModel.setEmailCustomer(emailCustomer);
//        customerModel.setPhoneCustomer(phoneCustomer);
//        customerModel.setAddressCustomer(addressCustomer);
//        customerModel.setOderDate(date);
//
//        orderCustomerModel.setPhoto(photo);
//        orderCustomerModel.setName(nameFabric);
//        orderCustomerModel.setDescription(description);
//        orderCustomerModel.setStructure(structure);
//        orderCustomerModel.setPaint(paint);
//        orderCustomerModel.setHeight(height);
//        orderCustomerModel.setColor(color);
//        orderCustomerModel.setQuantity(quantity);
//        orderCustomerModel.setPrice(price);
//        orderCustomerModel.setCustomerOrder(customerModel);
//
//        orderRepository.save(orderCustomerModel);
//        customerRepository.save(customerModel);

//    }

}
