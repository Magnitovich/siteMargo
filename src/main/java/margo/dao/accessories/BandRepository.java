package margo.dao.accessories;

import margo.model.accessories.BandModel;
import margo.model.interior.InteriorModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BandRepository extends CrudRepository<BandModel, Long> {

    List<BandModel> findByPhoto(String photo);

    List<BandModel> findByName(String name);
}
