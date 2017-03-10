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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerOrderRepository orderRepository;

    public CustomerDTO convertModelToDto(OrderCustomerModel model){

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomer_id(model.getCustomerOrder().getCustomer_id());
        customerDTO.setNameCustomer(model.getCustomerOrder().getNameCustomer());
        customerDTO.setPhoneCustomer(model.getCustomerOrder().getPhoneCustomer());
        customerDTO.setOderDate(model.getCustomerOrder().getOderDate());
        customerDTO.setAddressCustomer(model.getCustomerOrder().getAddressCustomer());
        customerDTO.setEmailCustomer(model.getCustomerOrder().getEmailCustomer());

        customerDTO.setName(model.getName());
        customerDTO.setPhoto(model.getPhoto());
        customerDTO.setPaint(model.getPaint());
        customerDTO.setPrice(model.getPrice());
        customerDTO.setColor(model.getColor());
        customerDTO.setHeight(model.getHeight());
        customerDTO.setStructure(model.getStructure());
        customerDTO.setDescription(model.getDescription());
        customerDTO.setQuantity(model.getQuantity());


        return customerDTO;
    }

    public List<CustomerDTO> convertModelToDTO(List<OrderCustomerModel> modelList){

        List<CustomerDTO> customerDTOs = new ArrayList<>();
        for(OrderCustomerModel model: modelList){
            CustomerDTO dto = convertModelToDto(model);
            customerDTOs.add(dto);
        }
        return customerDTOs;
    }

    public void addInfoAboutCustomerOrder(final String nameCustomer, final String emailCustomer, final String phoneCustomer,
                                          final String addressCustomer,
                                          final String photo, final String nameFabric, final String description,
                                          final String structure, final String paint, final String height,
                                          final String color, final Double quantity, final BigDecimal price){

        Date date = new Date();
        CustomerModel customerModel = new CustomerModel();
        OrderCustomerModel orderCustomerModel = new OrderCustomerModel();
        customerModel.setNameCustomer(nameCustomer);
        customerModel.setEmailCustomer(emailCustomer);
        customerModel.setPhoneCustomer(phoneCustomer);
        customerModel.setAddressCustomer(addressCustomer);
        customerModel.setOderDate(date);

        orderCustomerModel.setPhoto(photo);
        orderCustomerModel.setName(nameFabric);
        orderCustomerModel.setDescription(description);
        orderCustomerModel.setStructure(structure);
        orderCustomerModel.setPaint(paint);
        orderCustomerModel.setHeight(height);
        orderCustomerModel.setColor(color);
        orderCustomerModel.setQuantity(quantity);
        orderCustomerModel.setPrice(price);
        orderCustomerModel.setCustomerOrder(customerModel);

        orderRepository.save(orderCustomerModel);
        customerRepository.save(customerModel);

    }

}
