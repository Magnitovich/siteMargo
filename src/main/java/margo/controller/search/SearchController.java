package margo.controller.search;


import margo.model.modelDTO.allCurtainsDTO.AllFabricDTO;
import margo.service.search.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private SearchService searchService;



    @RequestMapping(value = "/searchOnTheSite", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView viewAllWhiskyInWarehouse(@RequestParam(value = "searchRequest", required = false)
                                                     String findSearchRequest) {

//        IfNull ifNothing = new IfNull();
//        System.out.println("IfNothink: must be null: "+ifNothing.getIfNothing());
//        System.out.println("We searching: " + findSearchRequest);
//        if (findSearchRequest == null){
//            System.out.println("We are finding - ZERRRO !!!");
//            findSearchRequest = ifNothing.getIfNothing();
//
//        }
//
//        ifNothing.setIfNothing(findSearchRequest);
//        System.out.println("IfNothink: must be NOT null: "+ifNothing.getIfNothing());


//        Iterable<WhiskyDTO> list = whiskyService.seeAllWhisky();
        List<AllFabricDTO> allFabricDTOs = searchService.searchAnswer(findSearchRequest);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("allSearch", allFabricDTOs);
        modelAndView.setViewName("searchAnswer");
        return modelAndView;
    }
//private class IfNull{
//    public String getIfNothing() {
//        return ifNothing;
//    }
//
//    public void setIfNothing(String ifNothing) {
//        this.ifNothing = ifNothing;
//    }
//
//    private String ifNothing=null;
//    }

}
