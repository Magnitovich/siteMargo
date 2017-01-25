package margo.dao.fabric;

import margo.model.allCurtains.TulleModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TulleRepository extends CrudRepository<TulleModel, Long>{


}
