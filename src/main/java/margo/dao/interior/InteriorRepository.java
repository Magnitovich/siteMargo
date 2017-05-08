package margo.dao.interior;

import margo.model.interior.InteriorModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InteriorRepository extends CrudRepository<InteriorModel, Long> {

    List<InteriorModel> findByPhoto(String photo);

    List<InteriorModel> findByName(String name);
}
