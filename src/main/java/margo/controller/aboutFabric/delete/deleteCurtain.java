package margo.controller.aboutFabric.delete;

import margo.controller.aboutFabric.CurtainController;
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
public class deleteCurtain {
    @Autowired
    private CurtainController curtainController;
    @Autowired
    private MainFinishedService service;

    @RequestMapping(value = "/delete/DELETE", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody List deleteCurtain(@RequestBody List<Long> namesDeleted){

        CrudRepository repository = curtainController.getRepository();
        service.delete(namesDeleted, repository);
        return namesDeleted;
}
}
