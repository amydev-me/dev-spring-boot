package iamcoda.example.springwebservicedemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        // All requests should be authenticated
        httpSecurity.authorizeHttpRequests(
                auth -> auth.anyRequest().authenticated()
                );
        // If a request is not authenticated, a web page is shown
        httpSecurity.httpBasic(Customizer.withDefaults());

        // CSRF disabled
        httpSecurity.csrf(csrf -> csrf.disable());
        return httpSecurity.build();
    }
}
