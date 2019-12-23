package net.enver.itcompanydemo.configuration;

import net.enver.itcompanydemo.security.jwt.JwtConfigurer;
import net.enver.itcompanydemo.security.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String LOGIN_ENDPOINT = "/api/v1/auth/**";
    private static final String USER_ENDPOINT = "/api/v1/users/**";
    private static final String MODERATOR_ENDPOINT = "/api/v1/moderator/**";
    private static final String ADMIN_ENDPOINT = "/api/v1/admin/**";

    private final JwtUtil jwtUtil;

    @Autowired
    public SecurityConfig(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .authorizeRequests()
                .antMatchers("/").permitAll()
                    .antMatchers(LOGIN_ENDPOINT).permitAll()
                    .antMatchers(USER_ENDPOINT).authenticated()
                    .antMatchers(MODERATOR_ENDPOINT).hasAnyRole("MODERATOR", "ADMIN")
                    .antMatchers(ADMIN_ENDPOINT).hasRole("ADMIN")
                    .anyRequest().authenticated()
                .and()
                    .apply(new JwtConfigurer(jwtUtil));
        http.headers().cacheControl();
    }
}
