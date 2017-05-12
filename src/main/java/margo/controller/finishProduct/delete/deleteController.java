package margo.controller.finishProduct.delete;

import margo.controller.finishProduct.BedroomController;
import margo.service.finishedProduct.MainFinishedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
public class deleteController {
    @Autowired
    private BedroomController bedroomController;
    @Autowired
    private MainFinishedService service;

    @RequestMapping(value = "/delete/finishProduct", method = {RequestMethod.POST})
    public @ResponseBody List deleteCurtain(@RequestBody List<Long> namesDeleted){

        CrudRepository repository = bedroomController.getRepository();

        service.delete(namesDeleted, repository);

    return namesDeleted;

}
}
