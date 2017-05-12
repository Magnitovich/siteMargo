package margo.controller.accessories.delete;

import margo.controller.accessories.AccessoriesController;
import margo.service.accessories.AccessoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class deleteAccessoriesController {
    @Autowired
    private AccessoriesController controller;
    @Autowired
    private AccessoriesService service;

    @RequestMapping(value = "/delete/accessories", method = {RequestMethod.POST})
    public @ResponseBody List deleteCurtain(@RequestBody List<Long> namesDeleted){

        CrudRepository repository = controller.getRepository();

        service.delete(namesDeleted, repository);

    return namesDeleted;

}
}
