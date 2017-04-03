package margo.config;

import margo.dao.UserRepository;
import margo.exception.UserNotAuthorizedException;
import margo.exception.UserNotFoundException;
import margo.model.user.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String useName)  {
        UserModel one = userRepository.findOne(useName);

        if (one == null) {
            List<UserModel> byEmail = userRepository.findByEmail(useName);
            UserModel userModel = byEmail.get(0);
            if (userModel ==null){
                throw new UsernameNotFoundException(useName);
            }else {
                return userModel;
            }
        }
        else if (one.getActiveTrue() == 0) {
            throw new UserNotAuthorizedException();
        } else {
            return one;
        }
    }
}
