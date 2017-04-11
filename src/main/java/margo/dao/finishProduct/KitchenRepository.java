package margo.dao.finishProduct;

import margo.model.finishedProduct.GuestroomModel;
import margo.model.finishedProduct.KitchenModel;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KitchenRepository extends PagingAndSortingRepository<KitchenModel, Long>{

    List<KitchenModel> findByPhoto(String photo);

    List<KitchenModel> findByName(String name);
    
}
