package margo.dao.cart;

import margo.model.cartOder.CustomerModel;
import margo.model.cartOder.OrderCustomerModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerOrderRepository extends CrudRepository<OrderCustomerModel, Long>{

    @Query("SELECT o FROM OrderCustomerModel o WHERE o.customerOrder.customer_id = :id")
//        @Query(value = "select  cs from OrderCustomerModel cs WHERE customer_id=?")
    List<OrderCustomerModel> findOrderCustomer(@Param("id") Long customer_id);

}
