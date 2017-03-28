package margo.service.cart;

import margo.dao.cart.CustomerOrderRepository;
import margo.dao.cart.CustomerRepository;
import margo.model.allCurtains.ClothFabricModel;
import margo.model.cartOder.CustomerModel;
import margo.model.cartOder.OrderCustomerModel;
import margo.model.cartOder.cartDTO.CustomerDTO;
import margo.model.modelDTO.allCurtainsDTO.ClothFabricDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerOrderRepository orderRepository;

    public CustomerDTO convertModelToDto(CustomerModel model){

        CustomerDTO customerDTO = new CustomerDTO();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        customerDTO.setCustomer_id(model.getCustomer_id());
        customerDTO.setNameCustomer(model.getNameCustomer());
        customerDTO.setPhoneCustomer(model.getPhoneCustomer());
        customerDTO.setOderDate(sdf.format(model.getOderDate()));
        customerDTO.setAddressCustomer(model.getAddressCustomer());
        customerDTO.setEmailCustomer(model.getEmailCustomer());
        customerDTO.setReciveOrder(model.getReciveOrder());
        customerDTO.setSentOrder(model.getSentOrder());

        return customerDTO;
    }

    public List<CustomerDTO> convertListModelToDTO(List<CustomerModel> modelList){

        List<CustomerDTO> customerDTOs = new ArrayList<>();
        for(CustomerModel model: modelList){
            CustomerDTO dto = convertModelToDto(model);
            customerDTOs.add(dto);
        }
        return customerDTOs;
    }
    public List<CustomerDTO> showAllCustomer(){
        Iterable<CustomerModel> customerModels = customerRepository.findAllFromDate();
        List<CustomerDTO> customerDTOs = convertListModelToDTO((List<CustomerModel>) customerModels);
        return customerDTOs;
    }
    public CustomerDTO seeSelectedCustomer(Long id){
       CustomerModel customerModel = customerRepository.findOne(id);
       CustomerDTO customerDTO = convertModelToDto(customerModel);
        return customerDTO;

    }
    public void deleteCustomerOrder(Long id){
        customerRepository.delete(id);
    }

    public void deleteOneOrderCustomer(Long id){
        orderRepository.delete(id);
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
