package margo.dao.fabric;

import margo.model.allCurtains.CurtainModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurtainRepository extends CrudRepository<CurtainModel, Long>{


}
