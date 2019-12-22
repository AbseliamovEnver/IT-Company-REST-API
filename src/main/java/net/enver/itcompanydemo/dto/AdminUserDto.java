package net.enver.itcompanydemo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import net.enver.itcompanydemo.model.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdminUserDto {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private BigDecimal salary;
    private Date birthday;
    private Date hiredDay;
    private Status status;
    private Set<Role> roles;
    private Set<Department> departments;
    private Set<Position> positions;

    public User toUser() {
        User user = new User();

        user.setId(id);
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setSalary(salary);
        user.setBirthday(birthday);
        user.setHiredDay(hiredDay);
        user.setStatus(status);
        user.setRoles(roles);
        user.setDepartments(departments);
        user.setPositions(positions);

        return user;
    }

    public static AdminUserDto fromUser(User user) {
        AdminUserDto adminUserDto = new AdminUserDto();

        adminUserDto.setId(user.getId());
        adminUserDto.setUsername(user.getUsername());
        adminUserDto.setFirstName(user.getFirstName());
        adminUserDto.setLastName(user.getLastName());
        adminUserDto.setSalary(user.getSalary());
        adminUserDto.setBirthday(user.getBirthday());
        adminUserDto.setHiredDay(user.getHiredDay());
        adminUserDto.setStatus(user.getStatus());
        adminUserDto.setRoles(user.getRoles());
        adminUserDto.setDepartments(user.getDepartments());
        adminUserDto.setPositions(user.getPositions());

        return adminUserDto;
    }

    public static List<AdminUserDto> adminUserDtoList(List<User> users) {
        List<AdminUserDto> userList = new ArrayList<>();
        users.forEach(user -> userList.add(fromUser(user)));
        return userList;
    }
}
