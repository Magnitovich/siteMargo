package margo.config;

import margo.dao.UserRepository;
import margo.filter.IsAccountNonExpiredFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(final HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()


                .antMatchers("/css/**", "/js/**", "/image/**")
                .permitAll()
                .antMatchers("/backgrounds/**")
                .permitAll()
                .antMatchers("/registrationPage")
                .permitAll()
                .antMatchers("/")
                .permitAll()
                .antMatchers("/addInfoAboutNewCurtain")
                .permitAll()
                .antMatchers("/history")
                .permitAll()
                .antMatchers("/clothModel")
                .permitAll()
                 .antMatchers("/allModel")
                .permitAll()
                 .antMatchers("/curtainModels")
                .permitAll()

                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .passwordParameter("password")
                .permitAll()
                //благодаря этой строчке при logout кидает на индекс, если ее удрать будет кидать на logout
                .and().logout().logoutSuccessUrl("/")
                .and()
                .httpBasic()
                .and()
                .csrf().disable();

        httpSecurity.rememberMe().rememberMeParameter("remember-me")
                .rememberMeCookieName("my-remember-me")
                .tokenRepository(persistentTokenRepository()).tokenValiditySeconds(86400000);


        httpSecurity.addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
    //    JDBC Authentication
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //receive login(userDetailsService) and password (passwordEncoder) from DB
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new MyPasswordEncoder();
    }


    public IsAccountNonExpiredFilter authenticationFilter() throws Exception {
        IsAccountNonExpiredFilter authFilter = new IsAccountNonExpiredFilter();
        authFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login","POST"));

        authFilter.setAuthenticationManager(super.authenticationManager());
        authFilter.setAuthenticationSuccessHandler(new SimpleUrlAuthenticationSuccessHandler("/"));
        authFilter.setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler("/login?error"));
        authFilter.setUsernameParameter("username");
        authFilter.setPasswordParameter("password");
        authFilter.setUserRepository(userRepository);
        return authFilter;
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }

}
