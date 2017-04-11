package margo.dao.finishProduct;

import margo.model.finishedProduct.BedroomModel;
import margo.model.finishedProduct.GuestroomModel;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BedroomRepository extends PagingAndSortingRepository<BedroomModel, Long>{

    List<BedroomModel> findByPhoto(String photo);

    List<BedroomModel> findByName(String name);
    
}
