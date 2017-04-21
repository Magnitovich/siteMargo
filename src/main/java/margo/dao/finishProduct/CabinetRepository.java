package margo.dao.finishProduct;

import margo.model.finishedProduct.CabinetModel;
import margo.model.finishedProduct.KitchenModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CabinetRepository extends CrudRepository<CabinetModel, Long> {

    List<CabinetModel> findByPhoto(String photo);

    List<CabinetModel> findByName(String name);
    
}
