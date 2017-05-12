package margo.dao.fabric;

import margo.model.allCurtains.ClothFabricModel;
import margo.model.allCurtains.CurtainModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface ClothFabricRepository extends CrudRepository<ClothFabricModel, Long>{

    List<ClothFabricModel> findByPhoto(String photo);
    List<ClothFabricModel> findById(Long id);

    List<ClothFabricModel> findByName(String name);

}
