package net.enver.itcompanydemo.dto.moderator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import net.enver.itcompanydemo.model.User;
import net.enver.itcompanydemo.model.UserStatus;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ModeratorUserDto {

    private Long id;
    private String username;
    private String phoneNumber;
    private UserStatus userStatus;

    public static ModeratorUserDto fromUser(User user) {
        ModeratorUserDto moderatorUserDto = new ModeratorUserDto();

        moderatorUserDto.setId(user.getId());
        moderatorUserDto.setUsername(user.getUsername());
        moderatorUserDto.setPhoneNumber(user.getPhoneNumber());
        moderatorUserDto.setUserStatus(user.getUserStatus());

        return moderatorUserDto;
    }

    public static List<ModeratorUserDto> moderatorUserDtoList(List<User> users) {
        List<ModeratorUserDto> userList = new ArrayList<>();
        users.forEach(user -> userList.add(fromUser(user)));
        return userList;
    }
}
