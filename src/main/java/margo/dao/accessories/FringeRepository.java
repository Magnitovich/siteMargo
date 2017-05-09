package margo.dao.accessories;

import margo.model.accessories.BandModel;
import margo.model.accessories.FringeModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FringeRepository extends CrudRepository<FringeModel, Long> {

    List<FringeModel> findByPhoto(String photo);

    List<FringeModel> findByName(String name);
}
