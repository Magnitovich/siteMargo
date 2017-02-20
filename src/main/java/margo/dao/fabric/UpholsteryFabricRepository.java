package margo.dao.fabric;

import margo.model.allCurtains.CurtainModel;
import margo.model.allCurtains.UpholsteryFabricModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UpholsteryFabricRepository extends CrudRepository<UpholsteryFabricModel, Long>{

    List<UpholsteryFabricModel> findByPhoto(String photo);


    List<UpholsteryFabricModel> findByName(String name);

}
