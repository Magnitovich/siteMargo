package margo.dao.finishProduct;

import margo.model.finishedProduct.LambrequinModel;
import margo.model.finishedProduct.TulleFinishedModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TulleFinishRepository extends CrudRepository<TulleFinishedModel, Long> {

    List<TulleFinishedModel> findByPhoto(String photo);

    List<TulleFinishedModel> findByName(String name);
    
}
