package margo.dao;

import margo.model.user.UserModel;
import margo.model.user.UserRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<UserModel, String>{

    @Query(value = "select  cs from UserModel cs WHERE name=?")
    List<UserModel> findByName(String name);

    @Query(value = "select  cs from UserModel cs WHERE EMAIL=?")
    List<UserModel> findByEmail(String email);


    @Query(value = "select  cs from UserModel cs WHERE VERIFICATION=?")
    List<UserModel> findByVerification(String verification);

}
