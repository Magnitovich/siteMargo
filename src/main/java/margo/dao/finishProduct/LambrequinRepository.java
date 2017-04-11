package margo.dao.finishProduct;

import margo.model.finishedProduct.KitchenModel;
import margo.model.finishedProduct.LambrequinModel;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LambrequinRepository extends PagingAndSortingRepository<LambrequinModel, Long>{

    List<LambrequinModel> findByPhoto(String photo);

    List<LambrequinModel> findByName(String name);
    
}
