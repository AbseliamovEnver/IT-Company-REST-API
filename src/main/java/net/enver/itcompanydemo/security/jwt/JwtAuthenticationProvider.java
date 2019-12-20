package net.enver.itcompanydemo.security.jwt;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JwtAuthenticationProvider /*extends AbstractUserDetailsAuthenticationProvider*/ {

//    @Override
//    protected void additionalAuthenticationChecks(UserDetails userDetails,
//                                                  UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken)
//            throws AuthenticationException {
//    }
//
////    @Override
//    protected UserDetails retrieveUser(String username,
//                                       UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken)
//            throws AuthenticationException {
//
//        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
//                .commaSeparatedStringToAuthorityList(String.valueOf(jwtUser.getRoles()));
//        new JwtUserDetails(
//                jwtUser.getId(),
//                jwtUser.getUsername(),
//                jwtUser.getFirstName(),
//                jwtUser.getLastName(),
//                jwtUser.getPassword(),
//                jwtUser.getSalary(),
//                jwtUser.getBirthday(),
//                jwtUser.getHiredDay(),
//                jwtUser.getStatus(),
//                jwtUser.getRoles(),
//                jwtUser.getDepartments(),
//                jwtUser.getPositions(),
//                token,
//                grantedAuthorities);
//
//        if (jwtUser == null){
//            throw new RuntimeException("JWT token is incorrect");
//        }
//        return null;
//    }
//
//    @Override
//    public boolean supports(Class<?> aClass) {
//        return JwtAuthenticationToken.class.isAssignableFrom(aClass);
//    }
}
