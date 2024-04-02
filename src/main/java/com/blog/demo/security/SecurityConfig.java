package com.blog.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.blog.demo.model.Role;
import com.blog.demo.service.UserInfoService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserInfoService();
    }

    @Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { 
    
        return http.csrf(c -> c.disable())
                        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                        .authorizeHttpRequests(request -> request.requestMatchers(jwtFilter.getIgnoreCsrfAntMatchers()).permitAll())

                        .authorizeHttpRequests(requests -> requests.requestMatchers("/register").hasAnyAuthority(Role.SYS_ADMIN.toString()))
                        .authorizeHttpRequests(requests -> requests.requestMatchers("/refresh-token").hasAnyAuthority(Role.SYS_ADMIN.toString(), Role.ADMIN.toString()))

                        .authorizeHttpRequests(requests -> requests.requestMatchers(HttpMethod.GET,"/recipes").permitAll())
                        .authorizeHttpRequests(requests -> requests.requestMatchers(HttpMethod.GET,"/recipes/**").permitAll())
                        .authorizeHttpRequests(requests -> requests.requestMatchers(HttpMethod.GET,"/recipes/tags/**").permitAll())
                        .authorizeHttpRequests(requests -> requests.requestMatchers(HttpMethod.GET,"/recipes/mealtypes/**").permitAll())
                        .authorizeHttpRequests(requests -> requests.requestMatchers(HttpMethod.POST,"/recipes").hasAnyAuthority(Role.SYS_ADMIN.toString()))
                        .authorizeHttpRequests(requests -> requests.requestMatchers(HttpMethod.PUT, "/recipes/**").hasAnyAuthority(Role.SYS_ADMIN.toString()))
                        .authorizeHttpRequests(requests -> requests.requestMatchers(HttpMethod.DELETE,"/recipes/**").hasAnyAuthority(Role.SYS_ADMIN.toString()))

                        // .authorizeHttpRequests(requests -> requests.requestMatchers(HttpMethod.GET,"/tags").hasAnyAuthority(Role.SYS_ADMIN.toString(), Role.ADMIN.toString()))
                        .authorizeHttpRequests(requests -> requests.requestMatchers(HttpMethod.GET,"/tags").permitAll())
                        .authorizeHttpRequests(requests -> requests.requestMatchers(HttpMethod.GET,"/tags/**").permitAll())

                        .authorizeHttpRequests(requests -> requests.requestMatchers(HttpMethod.GET,"/mealtypes").permitAll())
                        .authorizeHttpRequests(requests -> requests.requestMatchers(HttpMethod.GET,"/mealtypes/**").permitAll())
                        .authenticationProvider(authenticationProvider())
                        .build();


    //SYS_ADMIN has all, register, login
    //ADMIN has get all, get by, login

    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
