package net.enver.itcompanydemo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import net.enver.itcompanydemo.model.User;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    private String username;
    private String phoneNumber;

    public User toUser() {
        User user = new User();

        user.setUsername(username);
        user.setPhoneNumber(phoneNumber);

        return user;
    }

    public static UserDto fromUser(User user) {
        UserDto userDto = new UserDto();

        userDto.setUsername(user.getUsername());
        userDto.setPhoneNumber(user.getPhoneNumber());

        return userDto;
    }

    public static List<UserDto> userDtoList(List<User> users) {
        List<UserDto> userList = new ArrayList<>();
        users.forEach(user -> userList.add(fromUser(user)));
        return userList;
    }
}
