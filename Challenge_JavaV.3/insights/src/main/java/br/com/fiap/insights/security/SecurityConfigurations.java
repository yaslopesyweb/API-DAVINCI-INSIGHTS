package br.com.fiap.insights.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {


    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    private static final String[] SWAGGER_WHITELIS = { "/swagger-ui/**", "/v3/api-docs/**"};

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize

                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "opiniao/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "produto/**").permitAll()

                        .requestMatchers(HttpMethod.GET, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/auth/register").permitAll()
                        .requestMatchers(HttpMethod.GET, "cliente/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "opiniao/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "produto/**").permitAll()

                        .requestMatchers(HttpMethod.DELETE, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/auth/register").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "cliente/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "opiniao/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "produto/**").permitAll()

                        .requestMatchers(HttpMethod.PUT, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/auth/register").permitAll()
                        .requestMatchers(HttpMethod.PUT, "cliente/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "opiniao/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "produto/**").permitAll()

                        .requestMatchers(SWAGGER_WHITELIS).permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

}

