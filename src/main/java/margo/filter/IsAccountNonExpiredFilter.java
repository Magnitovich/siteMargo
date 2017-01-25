package margo.filter;

import margo.dao.UserRepository;
import margo.model.user.UserModel;
import margo.model.user.UserRole;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

//этот класс отслеживает действие логина и записывает в базу время логина
public class IsAccountNonExpiredFilter extends UsernamePasswordAuthenticationFilter {


    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private UserRepository userRepository;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        Authentication authentication = super.attemptAuthentication(request, response);

        Date now = new Date();
        UserModel user = (UserModel) authentication.getPrincipal(); //наполняем модель из SpringSecurity
        UserModel userModel = userRepository.findOne(user.getName());
        userModel.setLoginDate(now);
        userRepository.save(userModel);

        user.setLoginDate(now);

        return authentication;

    }
}
