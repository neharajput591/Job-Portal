package com.jobportal.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;

@Component
public class CustomAuthSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String redirectUrl = "/";

        System.out.println("Authorities size: " + authorities.size());

        for (GrantedAuthority authority : authorities) {
            String role = authority.getAuthority();

            System.out.println("Authority from Spring Security: " + role);
            // Debug log to see what roles are actually coming through
            System.out.println("User has authority: " + role);

            // Spring Security's builder.roles("ADMIN") results in "ROLE_ADMIN" authority
            // We'll check for both typical database values and Spring Security added
            // prefixes
            if (role.equalsIgnoreCase("ROLE_CANDIDATE") || role.equalsIgnoreCase("CANDIDATE")) {
                redirectUrl = "/candidate_home";
                break;
            } else if (role.equalsIgnoreCase("ROLE_RECRUITER") || role.equalsIgnoreCase("RECRUITER")) {
                redirectUrl = "/recruiter_home";
                break;
            } else if (role.equalsIgnoreCase("ROLE_ADMIN") || role.equalsIgnoreCase("ADMIN")) {
                redirectUrl = "/admin_home";
                break;
            }
        }

        System.out.println("Redirecting to: " + redirectUrl);
        response.sendRedirect(redirectUrl);
    }
}
