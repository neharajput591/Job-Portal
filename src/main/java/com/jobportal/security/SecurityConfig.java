package com.jobportal.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.jobportal.security.CustomUserDetailsService;

// package com.jobportal.security;

// // import org.springframework.beans.factory.annotation.Autowired;
// // import org.springframework.context.annotation.Bean;
// // import org.springframework.context.annotation.Configuration;
// // import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
// // import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// // import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// // import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// // import org.springframework.security.crypto.password.PasswordEncoder;
// // import org.springframework.security.web.SecurityFilterChain;

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
// //                           // ⭐ Role based pages
// //         .requestMatchers("/candidate_home/**").hasRole("CANDIDATE")
// //         .requestMatchers("/recruiter_home/**").hasRole("RECRUITER")
// //         .requestMatchers("/admin_home/**").hasRole("ADMIN")
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

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;

// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;

// import org.springframework.security.web.SecurityFilterChain;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {

//     @Autowired
//     private CustomAuthSuccessHandler customAuthSuccessHandler;

//     @Autowired
//     private CustomUserDetailsService customUserDetailsService;

//     @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

//         http
//             .csrf(csrf -> csrf.disable())
//             .authorizeHttpRequests(auth -> auth
//                     .requestMatchers("/", "/login", "/register", "/RegisterData" , "/images/**" , "/css/**" , "/js/**").permitAll()
//                     .anyRequest().authenticated())
//             .formLogin(form -> form
//                     .loginPage("/login")
//                     .loginProcessingUrl("/login")

//                     // 🔥 IMPORTANT — using email field
//                     .usernameParameter("email")
//                     .passwordParameter("password")

//                     // Role-based redirect
//                     .successHandler(customAuthSuccessHandler)
//                     .permitAll())
//             .logout(logout -> logout
//                     .logoutUrl("/logout")
//                     .logoutSuccessUrl("/")
//                     .permitAll());

//         return http.build();
//     }

//     @Bean
//     public DaoAuthenticationProvider authenticationProvider() {

//         DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

//         authProvider.setUserDetailsService(customUserDetailsService);
//         authProvider.setPasswordEncoder(passwordEncoder());

//         return authProvider;
//     }

//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }
// }

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    CustomAuthSuccessHandler customAuthSuccessHandler;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/login", "/register", "/RegisterData", "/images/**", "/css/**", "/js/**")
                        .permitAll()
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/login")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        // .defaultSuccessUrl("/candidate_home", true)
                        .successHandler(customAuthSuccessHandler)
                        .permitAll())
                .logout(logout -> logout.logoutSuccessUrl("/"));

        return http.build();
    }

    // ⭐ THIS ATTACHES AUTHENTICATION PROVIDER
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(customUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}