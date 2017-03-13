package margo.dao.fabric;

import margo.model.allCurtains.OrderCurtainModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderCurtainRepository extends CrudRepository<OrderCurtainModel, Long>{

    List<OrderCurtainModel> findByPhoto(String photo);
    List<OrderCurtainModel> findById(Long id);

    List<OrderCurtainModel> findByName(String name);


}
