package margo.dao.accessories;

import margo.model.accessories.PickupModel;
import margo.model.accessories.VariousModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VariousRepository extends CrudRepository<VariousModel, Long> {

    List<VariousModel> findByPhoto(String photo);

    List<VariousModel> findByName(String name);
}
