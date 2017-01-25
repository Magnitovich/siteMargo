package margo.dao.fabric;

import margo.model.allCurtains.UpholsteryFabricModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UpholsteryFabricRepository extends CrudRepository<UpholsteryFabricModel, Long>{


}
