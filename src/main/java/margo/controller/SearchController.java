package margo.controller;


import margo.model.modelDTO.allCurtainsDTO.AllFabricDTO;
import margo.service.search.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private SearchService searchService;


    List<AllFabricDTO> allFindingFabricDTOs = new ArrayList<>();

    @RequestMapping(value = "/searchOnTheSite/question", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView receiveQuestion(@RequestBody String findSearchRequest) {

//        System.out.println("We are SEARCHING: "+findSearchRequest);
        String[] str = findSearchRequest.split("\"");

        String search = str[1];
        allFindingFabricDTOs = searchService.searchAnswer(search);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("allThatWeCanSearch", allFindingFabricDTOs);
        modelAndView.setViewName("search/searchAnswer");

        return modelAndView;
    }

    @RequestMapping(value = "/searchOnTheSite", method = {RequestMethod.GET})
    public ModelAndView viewAllWhiskyInWarehouse() {

//        System.out.println(allFindingFabricDTOs);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("allThatWeCanSearch", allFindingFabricDTOs);
        modelAndView.setViewName("search/searchAnswer");
        return modelAndView;
    }
}
//
//    @RequestMapping(value = "/searchOnTheSite", method = {RequestMethod.GET, RequestMethod.POST})
//    public ModelAndView receiveQuestion(@RequestParam(value = "searchRequest")
//                                                     String findSearchRequest) {
//        System.out.println("We are SEARCHING: "+findSearchRequest);
//        List<AllFabricDTO> allFindingFabricDTOs = searchService.searchAnswer(findSearchRequest);
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("allThatWeCanSearch", allFindingFabricDTOs);
//        modelAndView.setViewName("search/searchAnswer");
//        return modelAndView;
//    }
