package margo.dao.fabric;

import margo.model.allCurtains.OrderCurtainModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderCurtainRepository extends CrudRepository<OrderCurtainModel, Long>{


}
