package net.enver.itcompanydemo.security.jwt;

import lombok.Data;
import net.enver.itcompanydemo.model.Department;
import net.enver.itcompanydemo.model.Position;
import net.enver.itcompanydemo.model.Status;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

@Data
public class JwtUserDetails implements UserDetails {

    private final Long id;
    private final String username;
    private final String firstName;
    private final String lastName;
    private final String password;
    private final BigDecimal salary;
    private final Date birthday;
    private final Date hiredDay;
    private final Status status;
    private final Set<Department> departments;
    private final Set<Position> positions;
    private final Collection<? extends GrantedAuthority> authorities;

    public JwtUserDetails(Long id, String username, String firstName, String lastName,
                          String password, BigDecimal salary, Date birthday, Date hiredDay, Status status,
                          Set<Department> departments, Set<Position> positions,
                          Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.salary = salary;
        this.birthday = birthday;
        this.hiredDay = hiredDay;
        this.status = status;
        this.departments = departments;
        this.positions = positions;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }
}
