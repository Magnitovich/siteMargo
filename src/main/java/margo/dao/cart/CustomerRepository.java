package margo.dao.cart;

import margo.model.cartOder.CustomerModel;
import margo.model.user.UserModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerModel, Long>{

//    @Query(value = "select  cs from CustomerModel cs WHERE name=?")
    List<CustomerModel> findByNameCustomer(String nameCustomer);

    @Query(value = "SELECT cm FROM CustomerModel cm ORDER BY oderDate DESC")
    List<CustomerModel> findAllFromDate();
//    @Query(value = "select  cs from CustomerModel cs WHERE EMAIL=?")
//    List<CustomerModel> findByEmail(String email);

//    List<CustomerModel> findByCUSTOMER_ID(Long id);


}
