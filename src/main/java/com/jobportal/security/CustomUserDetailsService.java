package com.jobportal.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Service;

import com.jobportal.model.User12;
import com.jobportal.repository.UserRepo12;

// // @Configuration
// // @EnableWebSecurity
// // public class SecurityConfig {

// //     @Autowired
// //     private CustomAuthSuccessHandler customAuthSuccessHandler;

// //     @Autowired
// //     private CustomUserDetailsService customUserDetailsService;

// //     @Bean
// //     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
// //         http
// //                 .csrf(csrf -> csrf.disable()) // Disabled for local development
// //                 .authorizeHttpRequests(auth -> auth
// //                         .requestMatchers("/", "/login", "/register", "/RegisterData").permitAll()
// //                         .anyRequest().authenticated())
// //                 .formLogin(form -> form
// //                         .loginPage("/login")
// //                         .loginProcessingUrl("/login")
// //                         // Using email field from the form as the username
// //                         .usernameParameter("email")
// //                         .passwordParameter("password")
// //                         // Checks the role and redirects accordingly
// //                         .successHandler(customAuthSuccessHandler)
// //                         .permitAll())
// //                 .logout(logout -> logout
// //                         .logoutUrl("/logout")
// //                         .logoutSuccessUrl("/")
// //                         .permitAll());

// //         return http.build();
// //     }

// //     @Bean
// //     public DaoAuthenticationProvider authenticationProvider() {
// //         DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
// //         authProvider.setUserDetailsService(customUserDetailsService);
// //         authProvider.setPasswordEncoder(passwordEncoder());
// //         return authProvider;
// //     }

// //     @Bean
// //     public PasswordEncoder passwordEncoder() {
// //         return new BCryptPasswordEncoder();
// //     }
// // }

// package com.jobportal.security;

// import java.util.Optional;

// import com.jobportal.model.User;
// import com.jobportal.repository.UserRepo;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.userdetails.*;
// import org.springframework.security.core.userdetails.User.UserBuilder;
// import org.springframework.stereotype.Service;

// import com.jobportal.model.User12;
// import com.jobportal.repository.UserRepo12;

// @Service
// public class CustomUserDetailsService implements UserDetailsService {

//     @Autowired
//     private UserRepo userRepository;

//     @Autowired
//     UserRepo12 user12Repo;

//     @Override
//     public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

//         System.out.println("Login attempt: " + email);

//         // Fetch user from database using email
//         // User dbUser = userRepository.findByEmail(email);
//         Optional<User12> dbUser = user12Repo.findByEmail(email);


//         if (dbUser == null) {
//             throw new UsernameNotFoundException("User not found");
            
//         }

//         // Convert your User entity into Spring Security User
//         UserBuilder builder = org.springframework.security.core.userdetails.User.withUsername(dbUser.getEmail());

//         builder.password(dbUser.getPassword());
//         builder.roles(dbUser.getRole()); // Candidate / Recruiter / Admin

//         return builder.build();
//     }
// }


// @Service
// public class CustomUserDetailsService implements UserDetailsService {

//     @Autowired
//     UserRepo12 user12Repo;

//     public UserDetails loadUserByUsername(String email)
//             throws UsernameNotFoundException {

//         System.out.println("Login attempt: " + email);

//         Optional<User12> optionalUser = user12Repo.findByEmail(email);

//         // ✅ Correct check
//         if (optionalUser.isEmpty()) {
//             throw new UsernameNotFoundException("User not found");
//         }

//         // ✅ Extract User12 object
//         User12 dbUser = optionalUser.get();

//         // ✅ Build Spring Security User
//         return org.springframework.security.core.userdetails.User
//                 .withUsername(dbUser.getEmail())
//                 .password(dbUser.getPassword())
//                 .roles(dbUser.getRole())
//                 .build();
//     }
// }


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo12 user12Repo;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        User12 dbUser = user12Repo.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found"));


        System.out.println("Role from DB: " + dbUser.getRole());
        return org.springframework.security.core.userdetails.User
                .withUsername(dbUser.getEmail())
                .password(dbUser.getPassword())
                .roles(dbUser.getRole().trim().toUpperCase())
                .build();
    }
}