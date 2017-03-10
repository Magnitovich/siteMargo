package margo.filter;


import margo.dao.UserRepository;
import margo.exception.UserNotAuthorizedException;
import margo.model.user.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CheckUserRegistration {

    @Autowired
    private UserRepository userRepository;

    private String email;

    private String userName="";

    public void checkAuthenticationUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //этот код проверяет пользователь отославший запрос существует ли в нашей БД(залогиненный)
        if (authentication.getPrincipal() instanceof UserModel) {

            //этим кодом мы будем вылогинивать пользователя через определенный промежуток времени,
            // если он не проявляет активности на сайте
            //now это время сейчас

            UserModel user = (UserModel) authentication.getPrincipal();

            userName=user.getName();

             if (user.getActiveTrue() == 0) {
                throw new UserNotAuthorizedException();

            } else {

                email = user.getEmail();
            }
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

}