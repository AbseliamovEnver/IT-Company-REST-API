package net.enver.itcompanydemo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import net.enver.itcompanydemo.model.User;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRegisterDto {

    private Long id;
    private String username;
    private String password;
    private String phoneNumber;

    public User toUser() {
        User user = new User();

        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);
        user.setPhoneNumber(phoneNumber);

        return user;
    }
}
