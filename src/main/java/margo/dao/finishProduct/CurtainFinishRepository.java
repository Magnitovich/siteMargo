package margo.dao.finishProduct;

import margo.model.finishedProduct.CurtainFinishedModel;
import margo.model.finishedProduct.TulleFinishedModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurtainFinishRepository extends CrudRepository<CurtainFinishedModel, Long> {

    List<CurtainFinishedModel> findByPhoto(String photo);

    List<CurtainFinishedModel> findByName(String name);
    
}
