package hu.hardcore.FPEweblap.config;

import hu.hardcore.FPEweblap.security.JWTGeneratorFilter;
import hu.hardcore.FPEweblap.security.JWTAuthenticationFilter;
import hu.hardcore.FPEweblap.service.UserService;
import hu.hardcore.FPEweblap.util.SecurityUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final SecurityUtil securityUtil;

    private final UserService userService;

    private final AuthenticationConfiguration authenticationConfiguration;

    public SecurityConfiguration(SecurityUtil securityUtil, UserService userService, AuthenticationConfiguration authenticationConfiguration) {
        this.securityUtil = securityUtil;
        this.userService = userService;
        this.authenticationConfiguration = authenticationConfiguration;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration config = new CorsConfiguration();
                        config.setAllowedOriginPatterns(Collections.singletonList("*"));
                        config.setAllowedMethods(Collections.singletonList("*"));
                        config.setAllowCredentials(true);
                        config.setAllowedHeaders(Collections.singletonList("*"));
                        config.setExposedHeaders(Arrays.asList("Authorization"));
                        config.setMaxAge(3600L);
                        return config;
                    }
                }))
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/login**").permitAll()
                .requestMatchers("/error").permitAll()
                .requestMatchers("/public/**").permitAll()
                .requestMatchers("/register").permitAll()
                .requestMatchers("/newsPage/**").hasRole("ADMIN")
                .requestMatchers("/events/**").hasRole("ADMIN")
                .requestMatchers("/gallery/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTGeneratorFilter(authenticationConfiguration.getAuthenticationManager()))
                .addFilter(new JWTAuthenticationFilter(authenticationConfiguration.getAuthenticationManager()))
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().disable()
                .httpBasic().disable();

        return http.build();
    }
    






}
