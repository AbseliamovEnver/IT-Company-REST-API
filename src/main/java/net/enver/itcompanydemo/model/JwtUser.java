package net.enver.itcompanydemo.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
public class JwtUser {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String phoneNumber;
    private BigDecimal salary;
    private Date birthday;
    private Date hiredDay;
    private Status status;
    private Set<Role> roles;
    private Set<Department> departments;
    private Set<Position> positions;
}
