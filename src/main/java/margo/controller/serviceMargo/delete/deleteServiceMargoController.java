package margo.controller.serviceMargo.delete;

import margo.service.interior.InteriorService;
import margo.service.serviceMargo.ServiceMargo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class deleteServiceMargoController {

    @Autowired
    private ServiceMargo service;

    @RequestMapping(value = "/delete/serviceMargo", method = {RequestMethod.POST})
    public @ResponseBody List deleteCurtain(@RequestBody List<Long> namesDeleted){

        service.delete(namesDeleted);

    return namesDeleted;

}
}
