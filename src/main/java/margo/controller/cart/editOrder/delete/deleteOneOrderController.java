package margo.controller.cart.editOrder.delete;

import margo.controller.cart.editOrder.OrderFromCustomerController;
import margo.model.cartOder.cartDTO.CustomerDTO;
import margo.model.cartOder.cartDTO.OrderCustomerDTO;
import margo.service.cart.CustomerService;
import margo.service.cart.OrderCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class DeleteOneOrderController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private OrderFromCustomerController fromCustomerController;
    @Autowired
    private OrderCustomerService orderCustomerService;

    private Long idForOrder;

    @RequestMapping(value = "/delete/oneOrderFromCustomer", method = {RequestMethod.GET, RequestMethod.POST})
    public  @ResponseBody Long receiveIdForDelete(@RequestBody Long name){
//    public @ResponseBody ModelAndView receiveIdForDelete(@RequestParam(value = "deleteForOrderCustomer") Long name){

        ModelAndView modelAndView = new ModelAndView();
        customerService.deleteOneOrderCustomer(name);
        Long id = fromCustomerController.getIdCustomer();
        idForOrder = id;
        return idForOrder;
    }

    public Long getIdForOrder() {
        return idForOrder;
    }
}
//        ModelAndView modelAndView = new ModelAndView();
//        List<OrderCustomerDTO> orderCustomerDTOs = orderCustomerService.seeSelectedCustomer(id);
//        CustomerDTO customerDTO = customerService.seeSelectedCustomer(id);
//        modelAndView.addObject("customerInfo", customerDTO);
//        modelAndView.addObject("selected", orderCustomerDTOs);
////        modelAndView.addObject("idDelete", id);
//        modelAndView.setViewName("cart/order/customerOrder");
