package margo.dao.fabric;

import margo.model.allCurtains.CurtainModel;
import margo.model.allCurtains.OrderCurtainModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderCurtainRepository extends CrudRepository<OrderCurtainModel, Long>{

    List<OrderCurtainModel> findByPhoto(String photo);


    List<OrderCurtainModel> findByName(String name);


}
