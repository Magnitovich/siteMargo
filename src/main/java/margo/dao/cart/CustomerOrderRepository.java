package margo.dao.cart;

import margo.model.cartOder.CustomerModel;
import margo.model.cartOder.OrderCustomerModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerOrderRepository extends CrudRepository<OrderCustomerModel, Long>{

//    @Query(value = "select  cs from OrderCustomerModel cs WHERE name=?")
//    List<OrderCustomerModel> findByName(String name);
//
//    @Query(value = "select  cs from OrderCustomerModel cs WHERE EMAIL=?")
//    List<OrderCustomerModel> findByEmail(String email);


}
