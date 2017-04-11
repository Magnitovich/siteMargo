package margo.controller.cart.editOrder;

import margo.controller.cart.editOrder.paging.Pager;
import margo.model.cartOder.CustomerModel;
import margo.model.cartOder.cartDTO.CustomerDTO;
import margo.service.cart.CustomerService;
import margo.service.pagination.PaginationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class ShowInfoAboutPurchasesController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private PaginationService paginationService;

    private static final int BUTTONS_TO_SHOW = 5;
    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 5;
    private static final int[] PAGE_SIZES = { 5, 10, 20, 50, 100 };

    @RequestMapping(value = "administrationNotSleeps", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView showFirst(@RequestParam("pageSize") Optional<Integer> pageSize,
                                  @RequestParam("page") Optional<Integer> page){

        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
//        if (pageSize != null) {
//            evalPageSize = pageSize
//        } else { evalPageSize = INITIAL_PAGE_SIZE}
//        from UI page start from 1, in Java code start from 0, because of this I use page.get() - 1
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
//        int evalPage;
//        {   final int tmpPage;
//            if (page.isPresent()) { //page.isPresent() = (page != null)
//                tmpPage = page;
//            } else {   tmpPage = 0;
//            }
//            if (tmpPage < 1) {
//                evalPage = INITIAL_PAGE;
//            } else {
//                evalPage = page.get()-1;
//            }
//        }

        Page<CustomerModel> persons = paginationService.findAllCurtainPageable(new PageRequest(evalPage, evalPageSize));
        Pager pager = new Pager(persons.getTotalPages(), persons.getNumber(), BUTTONS_TO_SHOW);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("persons", persons);
        modelAndView.addObject("selectedPageSize", evalPageSize);
        modelAndView.addObject("pageSizes", PAGE_SIZES);
        modelAndView.addObject("pager", pager);

        List<CustomerDTO> customerDTOs = customerService.showAllCustomer();
//        modelAndView.addObject("selected", customerDTOs);
        modelAndView.setViewName("cart/order/checkOrder");
        return modelAndView;
    }
}
