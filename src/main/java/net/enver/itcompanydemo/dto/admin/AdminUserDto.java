package net.enver.itcompanydemo.dto.admin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import net.enver.itcompanydemo.model.Role;
import net.enver.itcompanydemo.model.User;
import net.enver.itcompanydemo.model.UserStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdminUserDto {

    private Long id;
    private String username;
    private String phoneNumber;
    private UserStatus userStatus;
    private Set<Role> roles;
    private Date createdDate;
    private Date lastModifiedDate;

    public User toUser() {
        User user = new User();

        user.setId(id);
        user.setUsername(username);
        user.setPhoneNumber(phoneNumber);
        user.setRoles(roles);
        user.setUserStatus(userStatus);
        user.setCreatedDate(createdDate);
        user.setLastModifiedDate(lastModifiedDate);

        return user;
    }

    public static AdminUserDto fromUser(User user) {
        AdminUserDto adminUserDto = new AdminUserDto();

        adminUserDto.setId(user.getId());
        adminUserDto.setUsername(user.getUsername());
        adminUserDto.setPhoneNumber(user.getPhoneNumber());
        adminUserDto.setRoles(user.getRoles());
        adminUserDto.setUserStatus(user.getUserStatus());
        adminUserDto.setCreatedDate(user.getCreatedDate());
        adminUserDto.setLastModifiedDate(user.getLastModifiedDate());

        return adminUserDto;
    }

    public static List<AdminUserDto> adminUserDtoList(List<User> users) {
        List<AdminUserDto> userList = new ArrayList<>();
        users.forEach(user -> userList.add(fromUser(user)));
        return userList;
    }
}
