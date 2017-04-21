package margo.dao.finishProduct;

import margo.model.allCurtains.CurtainModel;
import margo.model.finishedProduct.GuestroomModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuestroomRepository extends CrudRepository<GuestroomModel, Long> {

    List<GuestroomModel> findByPhoto(String photo);

    List<GuestroomModel> findByName(String name);

}
