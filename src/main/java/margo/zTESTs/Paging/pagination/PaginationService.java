//package margo.zTESTs.Paging.pagination;
//
//import margo.dao.fabric.CurtainRepository;
//import margo.model.allCurtains.CurtainModel;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//import javax.transaction.Transactional;
//
//@Service
//public class PaginationService {
//
//    @Autowired
//    private CurtainRepository repository;
//
//    private static final int PAGE_SIZE = 5;
//
//
//    @Transactional
//    public Page<CurtainModel> findAllCurtainPageable(Pageable pageable) {
//        Page<CurtainModel> all = repository.findAll(pageable);
//
//        return all;
//    }
//}
//
//
//
//
////
////    public Page<CurtainModel> findAllRows(Integer pageNumber) {
////        int allRows = repository.findAllRows(pageable);
//////        System.out.println("CurtainService");
//////        System.out.println("Select count rows: "+allRows);
////        Double pagesSize = 10.0;
////        double quantityPages = Math.ceil(allRows/pagesSize);
//////        System.out.println("Matn.ceil: "+quantityPages);
////
////        PageRequest request = new PageRequest(pageNumber-1, PAGE_SIZE, Sort.Direction.ASC,"name");
////        Page<CurtainModel> all = repository.findAll(request);
////        return all;
////    }
////    @Transactional
////    public Page<CurtainModel> findAllPageable(Pageable pageable){
////        Page<CurtainModel> allRows = repository.findAllRows(pageable);
////        return allRows;
////    }