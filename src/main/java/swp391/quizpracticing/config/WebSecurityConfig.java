package swp391.quizpracticing.config;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import swp391.quizpracticing.security.SimpleAuthenticationSuccessHandler;
import swp391.quizpracticing.service.IUserService;

import java.io.IOException;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {
       @Autowired
        private SimpleAuthenticationSuccessHandler successHandler;
        @Autowired
        private IUserService userService;


        public WebSecurityConfig(@Lazy IUserService userService, @Lazy SimpleAuthenticationSuccessHandler successHandler) {
            super();
            this.successHandler = successHandler;
            this.userService = userService;
        }

        @Bean
        public BCryptPasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Bean
        public DaoAuthenticationProvider authenticationProvider() {
            DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
            auth.setUserDetailsService(userService);
            auth.setPasswordEncoder(passwordEncoder());
            return auth;
        }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(
                        AbstractHttpConfigurer::disable
                ).authorizeHttpRequests(
                        auth -> auth.requestMatchers("/home", "/login").permitAll() // với endpoint /hello thì sẽ được cho qua
                                .requestMatchers("/users/**").authenticated() // với endpoint /customer/** sẽ yêu cầu authenticate
                                .requestMatchers("/admin/**").authenticated()
                )
                .formLogin(
                        login -> login
                                .defaultSuccessUrl("/home")
                )
                .build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/static/**", "/templates/**");
    }
}
