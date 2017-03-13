package margo.dao.fabric;

import margo.model.allCurtains.TulleModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TulleRepository extends CrudRepository<TulleModel, Long>{

    List<TulleModel> findByPhoto(String photo);

    List<TulleModel> findById(Long id);

    List<TulleModel> findByName(String name);


}
