package margo.dao.fabric;

import margo.model.allCurtains.ClothFabricModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClothFabricRepository extends CrudRepository<ClothFabricModel, Long>{

}
