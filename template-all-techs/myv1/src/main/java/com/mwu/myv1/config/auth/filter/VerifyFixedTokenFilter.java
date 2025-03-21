package com.mwu.myv1.config.auth.filter;

import com.mwu.myv1.config.auth.CustomUserDetails;
import com.mwu.myv1.config.auth.JwtTokenProvider;
import com.mwu.myv1.config.auth.service.UserService;
import com.mwu.myv1.constant.AppConstants;
import io.jsonwebtoken.Claims;
import io.lettuce.core.models.stream.ClaimedMessages;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Slf4j
@Order(3)
public class VerifyFixedTokenFilter  extends OncePerRequestFilter {


    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserService userService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            String jwt = parseJwt(request);
            Claims claims = jwtTokenProvider.getJwtTokenClaim(jwt);
            if (Objects.nonNull(claims)) {
                Long userId = Long.parseLong(claims.getSubject());
                CustomUserDetails customUserDetails = userService.getUserDetailByUserId(userId);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
                authenticationToken.setDetails(customUserDetails);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        } catch (Exception e) {
            log.error("Cannot set user authentication: {}", e);
        }
        filterChain.doFilter(request, response);


    }

    private String parseJwt(HttpServletRequest request) {
        String bearerToken = request.getHeader(AppConstants.JWT_TOKEN_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
