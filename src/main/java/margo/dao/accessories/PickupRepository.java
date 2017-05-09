package margo.dao.accessories;

import margo.model.accessories.LuversModel;
import margo.model.accessories.PickupModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PickupRepository extends CrudRepository<PickupModel, Long> {

    List<PickupModel> findByPhoto(String photo);

    List<PickupModel> findByName(String name);
}
