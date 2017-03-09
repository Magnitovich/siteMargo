package margo.dao.cart;

import margo.model.cartOder.CustomerModel;
import margo.model.user.UserModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerModel, String>{

    @Query(value = "select  cs from CustomerModel cs WHERE name=?")
    List<CustomerModel> findByName(String name);

//    @Query(value = "select  cs from CustomerModel cs WHERE EMAIL=?")
//    List<CustomerModel> findByEmail(String email);


}
