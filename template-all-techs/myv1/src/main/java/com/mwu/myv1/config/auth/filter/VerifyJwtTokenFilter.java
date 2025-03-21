package com.mwu.myv1.config.auth.filter;


import com.mwu.myv1.config.auth.CustomUserDetails;
import com.mwu.myv1.config.auth.service.UserService;
import com.mwu.myv1.constant.AppConstants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Order(2)
public class VerifyJwtTokenFilter extends OncePerRequestFilter {

    @Value("${verified_tokens}")
    private Set<String> tokenSet;

    @Autowired
    private UserService userService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            if (verifyJwtToken(getRequestToken(request))){
                CustomUserDetails userDetails = userService.
                        loadDefaultUserForFixedTokenAuth();
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        filterChain.doFilter(request, response);
    }

    private Optional<String> getRequestToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(AppConstants.FIXED_TOKEN_HEADER));
    }

    private boolean verifyJwtToken(Optional<String> token) {

        boolean result = false;
        if (token.isPresent()) {
            result = this.tokenSet.contains(token.get());
        }
        return result;
    }
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getServletPath().equals("/api");
    }
}
