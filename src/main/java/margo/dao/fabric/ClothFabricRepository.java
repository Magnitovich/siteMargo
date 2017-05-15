package margo.dao.fabric;

import margo.model.allCurtains.ClothFabricModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ClothFabricRepository extends CrudRepository<ClothFabricModel, Long>{

    List<ClothFabricModel> findByPhoto(String photo);
    List<ClothFabricModel> findById(Long id);

//    List<ClothFabricModel> findByName(String name);
    @Query(value = "select cs from ClothFabricModel cs where name=?")
    List<ClothFabricModel> findByName(String name);

}
