package margo.controller.cart.editOrder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TestController {

    @RequestMapping(value = "/testOrderValue", method = RequestMethod.POST)
    public void receiveSomeId(@RequestBody Long name){
        System.out.println(name);
    }
}
