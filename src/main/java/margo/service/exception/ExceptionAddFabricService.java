//package margo.service.exception;
//
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.math.BigDecimal;
//import java.util.List;
//
//@Service
//public class ExceptionAddFabricService {
//
//    @Autowired
//    private CurtainRepository repository;
//    @Autowired
//    private CurtainService yachtService;
//    @Autowired
//    private CurtainController yachtsController;
//
//    public void compareEnterInfoAndInDB(final String photo, final String photo01, final String photo02,
//                                        final String photo03, final String photo04, final String photo05,
//                                        final String name, final String describe,
//                                                     final Double quantity, final BigDecimal price) {
//
//        List<CurtainDTO> listPhoto = yachtService.viewPhoto(photo);
////        List<CurtainDTO> listName = yachtService.viewName(name);
//
////        if (listPhoto.size()==0 && listName.size()==0) {
//        if (listPhoto.size()==0 ) {
//
//            yachtService.addNewCurtain(photo,photo01,photo02,photo03,photo04,photo05, name,describe,quantity,price);
////            ModelAndView modelAndView = yachtsController.viewListYachts();
//
//        } else {
//            throw new RuntimeException("WOW");
//        }
//    }
//
//}
