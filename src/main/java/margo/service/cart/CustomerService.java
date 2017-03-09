package margo.service.cart;

import margo.model.allCurtains.ClothFabricModel;
import margo.model.cartOder.CustomerModel;
import margo.model.cartOder.OrderCustomerModel;
import margo.model.cartOder.cartDTO.CustomerDTO;
import margo.model.modelDTO.allCurtainsDTO.ClothFabricDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CustomerService {

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
    public void reservInformation(final String nameCustomer, final String phone, final String address,
                                  final String email){
        CustomerDTO customerDTO = new CustomerDTO();

        Date oderDate = new Date();
        customerDTO.setNameCustomer(nameCustomer);
        customerDTO.setPhoneCustomer(phone);
        customerDTO.setOderDate(oderDate);
        customerDTO.setAddressCustomer(address);
        customerDTO.setEmailCustomer(email);

    }
    public List<CustomerDTO> convertModelToDTO(List<OrderCustomerModel> modelList){

        List<CustomerDTO> customerDTOs = new ArrayList<>();
        for(OrderCustomerModel model: modelList){
            CustomerDTO dto = convertModelToDto(model);
            customerDTOs.add(dto);
        }
        return customerDTOs;
    }


}
