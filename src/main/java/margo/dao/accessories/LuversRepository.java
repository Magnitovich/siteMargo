package margo.dao.accessories;

import margo.model.accessories.FringeModel;
import margo.model.accessories.LuversModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LuversRepository extends CrudRepository<LuversModel, Long> {

    List<LuversModel> findByPhoto(String photo);

    List<LuversModel> findByName(String name);
}
