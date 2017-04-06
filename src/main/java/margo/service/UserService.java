package margo.service;

import margo.dao.UserRepository;
import margo.exception.EmailCompareWithDBException;
import margo.model.user.UserModel;
import margo.service.sendEmail.SendingMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private SendingMail changePassword;
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

    public void sendChangeFormToEmailUser(String email) throws MessagingException {
        List<UserModel> byEmail = repository.findByEmail(email);

        if (byEmail.size()==0){
            throw new EmailCompareWithDBException();
        } else {
            String verification = UUID.randomUUID().toString();
            UserModel userModel = byEmail.get(0);
            userModel.setVerification(verification);
            repository.save(userModel);

            String subject = "Change Password";
            String confirmationUrl =  "<a href='" +
                    "http://localhost:8080/changePassword.html?verification="
                    + verification+"'>Нажмите для изменения пароля</a>";
            changePassword.sendingMessage(email,confirmationUrl,subject);
        }
    }
    public void changePassword(String verificationFromUI, String password) {
        List<UserModel> byVerification = repository.findByVerification(verificationFromUI);
        UserModel userModel = byVerification.get(0);
        userModel.setPassword(password);
        repository.save(userModel);
    }
}
