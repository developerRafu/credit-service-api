package com.creditproject.creditserviceapi.filters;

import com.creditproject.creditserviceapi.components.JWTUtil;
import com.creditproject.creditserviceapi.exceptions.InvalidTokenException;
import com.creditproject.creditserviceapi.exceptions.UnauthorizedUserException;
import com.creditproject.creditserviceapi.vo.enums.RequestEnums;
import com.creditproject.creditserviceapi.vo.enums.TokenEnum;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JWTUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain) throws ServletException, IOException {
        final var authHeader = request.getHeader(RequestEnums.AUTHORIZATION_HEADER.getValue());
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        final var token = authHeader.substring(7);
        final var email = jwtUtil.getUsername(token);
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            final var userDetails = this.userDetailsService.loadUserByUsername(email);
            final var authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
        filterChain.doFilter(request, response);
    }
}