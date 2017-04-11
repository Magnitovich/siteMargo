//package margo.zTESTs.Paging.controller;
//
//import margo.model.allCurtains.CurtainModel;
//import margo.model.modelDTO.allCurtainsDTO.CurtainDTO;
//import margo.service.fabric.CurtainService;
//import margo.zTESTs.Paging.pagination.PaginationService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Controller
//public class PagingController {
////
//    @Autowired
//    private PaginationService paginationService;
//    @Autowired
//    private CurtainService curtainService;
//
//    private static final int BUTTONS_TO_SHOW = 3;
//    private static final int INITIAL_PAGE = 0;
//    private static final int INITIAL_PAGE_SIZE = 5;
//    private static final int[] PAGE_SIZES = { 5, 10, 20, 50, 100 };
//
//    @RequestMapping(value = "/pages",method = RequestMethod.GET)
//    public ModelAndView seePagination(@RequestParam("pageSize") Optional<Integer> pageSize,
//                                      @RequestParam("page") Optional<Integer> page){
//        ModelAndView modelAndView =  new ModelAndView();
//
//        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
////        if (pageSize != null) {
////            evalPageSize = pageSize
////        } else { evalPageSize = INITIAL_PAGE_SIZE}
////        from UI page start from 1, in Java code start from 0, because of this I use page.get() - 1
//        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
////        int evalPage;
////        {
////            final int tmpPage;
////            if (page.isPresent()) { //page.isPresent() = (page != null)
////                tmpPage = page;
////            } else {
////                tmpPage = 0;
////            }
////            if (tmpPage < 1) {
////                evalPage = INITIAL_PAGE;
////            } else {
////                evalPage = page.get()-1;
////            }
////        }
//
//        Page<CurtainModel> persons = paginationService.findAllCurtainPageable(new PageRequest(evalPage, evalPageSize));
//        System.out.println("TESTcontroller");
//        System.out.println("persons.getTotalPages() "+persons.getTotalPages());
//        System.out.println("persons.getNumber() "+persons.getNumber());
//        System.out.println("persons.getTotalElements() "+persons.getTotalElements());
//        System.out.println("persons.getNumberOfElements() "+persons.getNumberOfElements());
//        System.out.println("persons.getSize() "+persons.getSize());
//        System.out.println("persons.getSort() "+persons.getSort());
//
//        Pager pager = new Pager(persons.getTotalPages(), persons.getNumber(), BUTTONS_TO_SHOW);
//
//        modelAndView.addObject("persons", persons);
//        modelAndView.addObject("selectedPageSize", evalPageSize);
//        modelAndView.addObject("pageSizes", PAGE_SIZES);
//        modelAndView.addObject("pager", pager);
//
//        List<CurtainDTO> curtainDTOs = curtainService.seeAllModels();
//        ArrayList colorModel =curtainService.seeColor();
//        ArrayList paint =curtainService.seePaint();
//        ArrayList structure =curtainService.seeStructure();
//        ArrayList filterPrice = curtainService.seePrice();
////        modelAndView.addObject("allCurtain",curtainDTOs);
//        modelAndView.addObject("price",filterPrice);
//        modelAndView.addObject("forColor",colorModel);
//        modelAndView.addObject("forPaint",paint);
//        modelAndView.addObject("forStructure",structure);
//        modelAndView.setViewName("TESTs/paging");
//        return modelAndView;
//    }
//
//}
