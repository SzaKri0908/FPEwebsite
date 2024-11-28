package hu.hardcore.FPEweblap.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import hu.hardcore.FPEweblap.model.Role;
import hu.hardcore.FPEweblap.model.User;
import hu.hardcore.FPEweblap.model.dto.CredentialsDTO;
import hu.hardcore.FPEweblap.service.UserService;
import hu.hardcore.FPEweblap.util.BeanProvider;
import hu.hardcore.FPEweblap.util.ConstantStore;
import hu.hardcore.FPEweblap.util.ObjectMapperUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Slf4j
public class JWTGeneratorFilter extends UsernamePasswordAuthenticationFilter {

    private static final String TOKEN_ISSUER = "KitchenBuddy-Dev-Team";

    private static final String TOKEN_AUDIENCE = "KitchenBuddy-Client";

    private static final Integer TOKEN_EXPIRATION_TIME = 30000000;

    private final AuthenticationManager authenticationManager;


    private final ObjectMapper jsonMapper = new ObjectMapper();
    public JWTGeneratorFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;

        setFilterProcessesUrl(ConstantStore.AUTH_LOGIN_URL);
    }

    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        CredentialsDTO dto = ObjectMapperUtil.readValue(request.getInputStream(), CredentialsDTO.class);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());

        response.setHeader("Content-Type", ConstantStore.PRODUCES_JSON);

        try {
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            return authentication;
        }catch (InternalAuthenticationServiceException e){
            ObjectNode responseReason = ObjectMapperUtil.objectNode().put(ConstantStore.RESPONSE_REASON, "Hibás felhasználónév");
            response.getWriter().write(responseReason.toString());
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }catch (BadCredentialsException e){
            ObjectNode responseReason = ObjectMapperUtil.objectNode().put(ConstantStore.RESPONSE_REASON, "Hibás jelszó");
            response.getWriter().write(responseReason.toString());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return null;
        }
    }

    private void setResponse(String responseText, HttpServletResponse response) throws IOException {
        ObjectNode responseReason = ObjectMapperUtil.objectNode().put(ConstantStore.RESPONSE_REASON, responseText);
        response.getWriter().write(responseReason.toString());
        //response.setStatus(response);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, Authentication authentication) throws ServletException, IOException {
        User loggedInUser = BeanProvider.getBean(UserService.class).findUserByUsername(authentication.getName());
        List<Role> roles = loggedInUser.getRoles();

        SecretKey key = Keys.hmacShaKeyFor(ConstantStore.JWT_SECRET.getBytes(StandardCharsets.UTF_8));
        String token = Jwts.builder().setIssuer(TOKEN_ISSUER).setSubject("JWT")
                .claim("username", loggedInUser.getUsername())
                .claim("authorities", populateAuthorities(roles))

                .setIssuedAt(new Date())
                .setAudience(TOKEN_AUDIENCE)
                .setExpiration(new Date((new Date()).getTime() + TOKEN_EXPIRATION_TIME))
                .signWith(key).compact();
        try {
            response.setContentType(ConstantStore.PRODUCES_JSON);
            response.setHeader(ConstantStore.TOKEN_HEADER, token);
            ObjectNode responseReason = ObjectMapperUtil.objectNode().put(ConstantStore.RESPONSE_REASON, "Sikeres bejelentkezés");
            response.getWriter().write(responseReason.toString());
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            log.error("unsuccessfulAuthentication - ", e);
        }
        //filterChain.doFilter(request,response);
    }


    private String populateAuthorities(Collection<? extends GrantedAuthority> collection) {
        Set<String> authoritiesSet = new HashSet<>();
        for (GrantedAuthority authority : collection) {
            authoritiesSet.add(authority.getAuthority());
        }
        return String.join(",", authoritiesSet);
    }

}