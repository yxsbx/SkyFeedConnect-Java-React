package com.Ada.SFCAuthenticator.security.jwt;

import java.io.IOException;

import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.Ada.SFCAuthenticator.service.UserDatailServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * This class implements a filter for JWT authentication.
 */
public class AuthFilterToken extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDatailServiceImpl userDatailService;

    /**
     * Filters the HTTP requests to check for JWT tokens and authenticate users.
     * @param request The HTTP request.
     * @param response The HTTP response.
     * @param filterChain The filter chain.
     * @throws ServletException If a servlet exception occurs.
     * @throws IOException If an I/O exception occurs.
     */
    @Override
    protected void doFilterInternal(
          @Nonnull HttpServletRequest request,
          @Nonnull HttpServletResponse response,
          @Nonnull FilterChain filterChain) throws ServletException, IOException {

        String jwt = getToken(request);
        if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
            try {
                String username = jwtUtils.extractUsernameFromJwtToken(jwt);

                UserDetails userDetails = userDatailService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(auth);

            } catch (Exception e) {
                logger.error("Erro ao processar token JWT e configurar autenticação.", e);
                throw new ServletException("Falha ao processar token JWT e configurar autenticação.", e);
            }
        }
    filterChain.doFilter(request, response);
    }

    /**
     * Extracts the JWT token from the HTTP request header.
     * @param request The HTTP request.
     * @return The JWT token if present, otherwise null.
     */
    private String getToken(HttpServletRequest request) {
        String headerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(headerToken) && headerToken.startsWith("Bearer")) {
        return headerToken.replace("Bearer ", "");
        }
        return null;
    }
}
