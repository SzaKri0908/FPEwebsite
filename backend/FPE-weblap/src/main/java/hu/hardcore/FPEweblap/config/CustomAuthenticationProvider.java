package hu.hardcore.FPEweblap.config;

import hu.hardcore.FPEweblap.model.Role;
import hu.hardcore.FPEweblap.model.User;
import hu.hardcore.FPEweblap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

//@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
   ///@Autowired
    private UserService customerRepository;

    //@Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String pwd = authentication.getCredentials().toString();
        User user = customerRepository.findUserByUsername(username);
            if (passwordEncoder.matches(pwd, user.getPassword())) {
                return new UsernamePasswordAuthenticationToken(username, pwd, getGrantedAuthorities(user.getRoles()));
            } else {
                throw new BadCredentialsException("Invalid password!");
            }
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<Role> authorities) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Role authority : authorities) {
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getName()));
        }
        return grantedAuthorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
