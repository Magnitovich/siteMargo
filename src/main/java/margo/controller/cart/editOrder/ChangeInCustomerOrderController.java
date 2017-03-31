package margo.controller.cart.editOrder;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Pattern;
import margo.service.cart.OrderCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ChangeInCustomerOrderController {

    @Autowired
    private OrderCustomerService orderCustomerService;

    @RequestMapping(value = "/changeQuantityOrder", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody void seePageAddCurtain(@RequestParam(value = "idOrder") Long id,
                                          @RequestParam(value = "quantityChange") Double quantity) {

//        System.out.println("Id:"+id+" Quantity "+quantity );
        orderCustomerService.changeOrder(id, quantity);
    }


}
