package hu.hardcore.FPEweblap.security;

import hu.hardcore.FPEweblap.util.ConstantStore;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
public class JWTAuthenticationFilter extends BasicAuthenticationFilter {

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
        if (authentication == null) {
            filterChain.doFilter(request, response);
            return;
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(ConstantStore.TOKEN_HEADER);
        if (StringUtils.isNotEmpty(token)) {
            token = token.replaceAll("Bearer ", "");
            try {
                SecretKey key = Keys.hmacShaKeyFor(
                        ConstantStore.JWT_SECRET.getBytes(StandardCharsets.UTF_8));

                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(key)
                        .build()
                        .parseClaimsJws(token)
                        .getBody();
                String username = String.valueOf(claims.get("username"));
                String authorities = (String) claims.get("authorities");

                if (StringUtils.isNotEmpty(username)) {
                    return new UsernamePasswordAuthenticationToken(username, null, AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));
                }
            } catch (ExpiredJwtException exception) {
                log.warn("Expired JWT: {}", exception.getMessage());
            } catch (UnsupportedJwtException exception) {
                log.warn("Unsupported JWT: {}", exception.getMessage());
            } catch (MalformedJwtException exception) {
                log.warn("Invalid JWT: {}", exception.getMessage());
            } catch (IllegalArgumentException exception) {
                log.warn("Empty or null JWT: {}", exception.getMessage());
            }
        }

        return null;
    }

}