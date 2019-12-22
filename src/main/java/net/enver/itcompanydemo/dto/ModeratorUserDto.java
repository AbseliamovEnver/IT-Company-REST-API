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
public class ModeratorUserDto {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private BigDecimal salary;
    private Date birthday;
    private Date hiredDay;
    private Status status;
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
        user.setDepartments(departments);
        user.setPositions(positions);

        return user;
    }

    public static ModeratorUserDto fromUser(User user) {
        ModeratorUserDto moderatorUserDto = new ModeratorUserDto();

        moderatorUserDto.setId(user.getId());
        moderatorUserDto.setUsername(user.getUsername());
        moderatorUserDto.setFirstName(user.getFirstName());
        moderatorUserDto.setLastName(user.getLastName());
        moderatorUserDto.setSalary(user.getSalary());
        moderatorUserDto.setBirthday(user.getBirthday());
        moderatorUserDto.setHiredDay(user.getHiredDay());
        moderatorUserDto.setStatus(user.getStatus());
        moderatorUserDto.setDepartments(user.getDepartments());
        moderatorUserDto.setPositions(user.getPositions());

        return moderatorUserDto;
    }

    public static List<ModeratorUserDto> moderatorUserDtoList(List<User> users) {
        List<ModeratorUserDto> userList = new ArrayList<>();
        users.forEach(user -> userList.add(fromUser(user)));
        return userList;
    }
}
