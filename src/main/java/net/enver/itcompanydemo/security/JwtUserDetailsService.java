package net.enver.itcompanydemo.security;

import lombok.extern.slf4j.Slf4j;
import net.enver.itcompanydemo.exception.PhoneVerificationException;
import net.enver.itcompanydemo.model.Role;
import net.enver.itcompanydemo.model.User;
import net.enver.itcompanydemo.model.UserStatus;
import net.enver.itcompanydemo.security.jwt.JwtUserDetails;
import net.enver.itcompanydemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("In JwtUserDetailsService loadUserByUsername method.");

        User user = userService.findByUsername(username);

        if (user == null || user.getUserStatus() == UserStatus.DELETED) {
            throw new UsernameNotFoundException("Username: " + username + " not found");
        }

        if (user.getUserStatus() == UserStatus.INACTIVE) {
            throw new PhoneVerificationException("User with username: " + username + " is not verified.");
        }
        JwtUserDetails jwtUser = new JwtUserDetails(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getPhoneNumber(),
                grantedAuthorities(user.getRoles()));

        log.info("User with username: {} successfully loaded", username);
        return jwtUser;
    }

    private Set<GrantedAuthority> grantedAuthorities(Set<Role> roles) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return authorities;
    }
}
