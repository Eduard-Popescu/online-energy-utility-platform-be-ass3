package com.example.onlineenergyutilityplatform.security;

import com.example.onlineenergyutilityplatform.exception.ExpiredTokenException;
import com.example.onlineenergyutilityplatform.service.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class TokenValidatorFilter extends OncePerRequestFilter {

    private final UserService jwtUserDetailsService;
    private final TokenGenerator tokenGenerator;

    @Autowired
    public TokenValidatorFilter(UserService jwtUserDetailsService, TokenGenerator tokenGenerator) {
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.tokenGenerator = tokenGenerator;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException, IllegalArgumentException, ExpiredTokenException {
        String token = getToken(request).split("\\s+")[1];
        if (isMissing(token)) {
            forbidden(response);
            return;
        }

        String username = getUsernameFromToken(token);
        if (userIsNotAuthenticated(username)) {
            forbidden(response);
            return;
        }

        UserDetails userDetails = jwtUserDetailsService.loadUserByUserName(username);
        if (invalidToken(token, userDetails)) {
            forbidden(response);
            return;
        }

        UsernamePasswordAuthenticationToken authenticationToken = createAuthenticationToken(request, userDetails);
        setAuthenticationTokenInSecurityContext(authenticationToken);
        chain.doFilter(request, response);
    }

    private void forbidden(HttpServletResponse response) throws IOException {
        response.sendError(403, "Unauthorised");
    }

    private void setAuthenticationTokenInSecurityContext(UsernamePasswordAuthenticationToken authToken) {
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }

    private UsernamePasswordAuthenticationToken createAuthenticationToken(HttpServletRequest request, UserDetails userDetails) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return authToken;
    }

    private Boolean invalidToken(String token, UserDetails userDetails) {
        return !tokenGenerator.validateToken(token, userDetails);
    }

    public static String getToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    private boolean isMissing(String in) {
        return in == null || in.isBlank();
    }

    private String getUsernameFromToken(String jwtToken) {
        try {
            return tokenGenerator.getUsernameFromToken(jwtToken);
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new IllegalArgumentException("Unable to get Token.");
        } catch (ExpiredJwtException expiredJwtException) {
            throw new ExpiredTokenException("Token has expired.");
        }
    }

    private boolean userIsNotAuthenticated(String username) {
        return isMissing(username) || SecurityContextHolder.getContext().getAuthentication() != null;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getPathInfo();
        return path.equals("/login") || path.equals("/h2-console") || path.contains("/ws");
    }
}
