package margo.dao.finishProduct;

import margo.model.finishedProduct.CabinetModel;
import margo.model.finishedProduct.ChildrenroomModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChildrenRepository extends CrudRepository<ChildrenroomModel, Long> {

    List<ChildrenroomModel> findByPhoto(String photo);

    List<ChildrenroomModel> findByName(String name);
    
}
