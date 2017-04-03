package margo.service;

import margo.dao.UserRepository;
import margo.model.user.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public UserModel seeSelectedUser(String name){
        UserModel one = repository.findOne(name);
        return one;
    }
    public void changeData(String name, String address,  String phone, String email){
        UserModel one = repository.findOne(name);
        one.setPhone(phone);
        one.setDescription(address);
        one.setEmail(email);
        repository.save(one);
    }
}
