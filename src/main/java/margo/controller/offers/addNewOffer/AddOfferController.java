package margo.controller.offers.addNewOffer;

import margo.controller.MainController;
import margo.model.modelDTO.allCurtainsDTO.AllFabricDTO;
import margo.service.finishedProduct.MainFinishedService;
import margo.service.offer.MainOfferService;
import margo.service.offer.SelectRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.Date;

@Controller
public class AddOfferController {

    @Autowired
    private MainController mainController;
    @Autowired
    private MainOfferService offerService;
    @Autowired
    private SelectRepositoryService repositoryService;
    @Autowired
    private MainFinishedService service;

    private Long idCommodity = null;
    private String partCommodity = "";
    private CrudRepository repository = null;
    private AllFabricDTO margoDTO = new AllFabricDTO();

    @RequestMapping(value = "/addNewOffer", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView seePageAddCurtain(@RequestParam(required = false) Long id,
                                          @RequestParam(value = "part", required = false)String part) {
        ModelAndView modelAndView = new ModelAndView();
        partCommodity = part;
        idCommodity = id;
//        System.out.println("OFFER id: "+id+", photo: "+part);
        repository = repositoryService.selectRepository(part);
        margoDTO = service.findSelectedModel(id, repository);

//            margoDTO.setIdForEditCurtain(id);
            modelAndView.addObject("comparePhotoNameWithDB", margoDTO);
            modelAndView.setViewName("offers/addNewOffer");
            return modelAndView;

    }

    @RequestMapping(value = "/addInfoAboutNewOffer", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView seePageAddCurtain(@RequestParam(value = "percent") Integer percent,
//                                          @RequestParam(value = "quantityInOffer")Double quantity,
                                          @RequestParam(value = "dateOffer")
                                          @DateTimeFormat(pattern = "yyyy-MM-dd")Date dateOffer,
                                          @ModelAttribute("comparePhotoNameWithDB") AllFabricDTO dto,
                                          BindingResult result) {
        BigDecimal percentBigDec = BigDecimal.valueOf(percent);
        BigDecimal valuePercent = new BigDecimal("100");
        BigDecimal priceFromDB = margoDTO.getPrice();
        BigDecimal newPrice = priceFromDB.subtract((priceFromDB).multiply(percentBigDec).divide(valuePercent));
        try {
            offerService.receiveInfoAboutNewOffer(idCommodity, partCommodity, priceFromDB, dateOffer, newPrice,
                    repository,  margoDTO);
        } catch (RuntimeException r){
            result.rejectValue("name", "error.name", "Этот товар уже участвует в акции!");
            return viewException();
        }
        System.out.println("AddINFONewOffer");
        return mainController.viewMainPage();

    }
    public ModelAndView viewException() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("offers/addNewOffer");
        return modelAndView;
    }

    @ModelAttribute("comparePhotoNameWithDB")
    public AllFabricDTO createModel() {
        return new AllFabricDTO();
    }

    public Long getIdComodity() {
        return idCommodity;
    }

    public String getPartComodity() {
        return partCommodity;
    }
}

