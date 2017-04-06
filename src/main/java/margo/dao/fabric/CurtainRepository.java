package margo.dao.fabric;

import margo.model.allCurtains.CurtainModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurtainRepository extends CrudRepository<CurtainModel, Long>{

    List<CurtainModel> findByPhoto(String photo);

    List<CurtainModel> findById(Long id);

    List<CurtainModel> findByName(String name);

    @Query("SELECT count(e) FROM CurtainModel e")
    int findAllRows();
}
