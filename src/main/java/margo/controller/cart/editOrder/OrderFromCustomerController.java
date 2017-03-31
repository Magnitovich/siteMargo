package margo.controller.cart.editOrder;

import margo.controller.cart.editOrder.delete.DeleteOneOrderController;
import margo.model.cartOder.cartDTO.CustomerDTO;
import margo.model.cartOder.cartDTO.OrderCustomerDTO;
import margo.service.cart.CustomerService;
import margo.service.cart.OrderCustomerService;
import org.codehaus.groovy.runtime.powerassert.SourceText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class OrderFromCustomerController {

    @Autowired
    private OrderCustomerService orderCustomerService;
    @Autowired
    private CustomerService customerService;

    private Long idCustomer;
    @Autowired
    private DeleteOneOrderController deleteOneOrderController;

    @RequestMapping(value = "/orderFromCustomer", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView infoAboutCustomer(@RequestParam(value = "id")Long id){

        if (id ==0){
            ModelAndView modelAndView = imageResult(deleteOneOrderController.getIdForOrder());
            return modelAndView;
        } else {
            idCustomer = id;
            ModelAndView modelAndView = imageResult(id);
            return modelAndView;
        }
    }

    public Long getIdCustomer() {
        return idCustomer;
    }

    public ModelAndView imageResult(Long id){
        ModelAndView modelAndView = new ModelAndView();
        List<OrderCustomerDTO> orderCustomerDTOs = orderCustomerService.seeSelectedCustomer(id);
        CustomerDTO customerDTO = customerService.seeSelectedCustomer(id);
        modelAndView.addObject("customerInfo", customerDTO);
        modelAndView.addObject("selected", orderCustomerDTOs);
        modelAndView.setViewName("cart/order/customerOrder");
        return modelAndView;
    }
}
