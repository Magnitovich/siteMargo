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

@Service
public class UserDetailsImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String useName)  {
        UserModel one = userRepository.findOne(useName);

        if (one == null) {
            throw new UsernameNotFoundException(useName);

        }
        else if (one.getActiveTrue() == 0) {
            throw new UserNotAuthorizedException();

        } else {


            return one;
        }
    }
}
