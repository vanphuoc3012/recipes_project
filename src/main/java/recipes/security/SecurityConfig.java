package recipes.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .mvcMatchers("/api/register").permitAll()
                .mvcMatchers("/actuator/shutdown").permitAll()
                .mvcMatchers(HttpMethod.DELETE, "/api/recipe/**").hasRole("USER")
                .mvcMatchers(HttpMethod.PUT, "/api/recipe/**").hasRole("USER")
                .mvcMatchers("/api/recipe/**").hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .httpBasic();
        http.headers().frameOptions().disable();
        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthProvider = new DaoAuthenticationProvider();
        daoAuthProvider.setUserDetailsService(userDetailsService);
        daoAuthProvider.setPasswordEncoder(getEncoder());

        return daoAuthProvider;
    }

    @Bean
    public PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder(16);
    }
}
