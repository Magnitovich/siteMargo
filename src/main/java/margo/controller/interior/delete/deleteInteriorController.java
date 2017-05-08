package margo.controller.interior.delete;

import margo.controller.finishProduct.BedroomController;
import margo.controller.interior.InteriorController;
import margo.service.finishedProduct.MainFinishedService;
import margo.service.interior.InteriorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class deleteInteriorController {

    @Autowired
    private InteriorService service;

    @RequestMapping(value = "/delete/interior", method = {RequestMethod.POST})
    public @ResponseBody List deleteCurtain(@RequestBody List<Long> namesDeleted){

        service.delete(namesDeleted);

    return namesDeleted;

}
}
