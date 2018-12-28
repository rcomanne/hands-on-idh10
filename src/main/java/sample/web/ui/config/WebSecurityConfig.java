package sample.web.ui.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String USER = "USER";
    private static final String ADMIN = "ADMIN";
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .authorizeRequests()
            .antMatchers("/", "/home").permitAll()
            .antMatchers("/orders/create-catalog").hasRole(ADMIN)
            .anyRequest().authenticated()
            .and()
       .formLogin()
            .loginPage("/login")
            .permitAll()
            .and()
       .logout()
            .logoutSuccessUrl("/")
            .permitAll()
        .and()
            .exceptionHandling()
            .accessDeniedPage("/403");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
            .withUser("user").password(passwordEncoder().encode("password")).roles(USER)
            .and()
            .withUser("admin").password(passwordEncoder().encode("admin")).roles(USER, ADMIN);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
