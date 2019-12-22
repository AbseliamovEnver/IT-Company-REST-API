package net.enver.itcompanydemo.rest;

import net.enver.itcompanydemo.dto.ModeratorUserDto;
import net.enver.itcompanydemo.model.User;
import net.enver.itcompanydemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/moderator/")
public class ModeratorRestControllerV1 {
    private final UserService userService;

    @Autowired
    public ModeratorRestControllerV1(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ModeratorUserDto> getUserById(@PathVariable("id") Long userId) {
        if (userId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User user = this.userService.getById(userId);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ModeratorUserDto.fromUser(user), HttpStatus.OK);
    }

    @GetMapping(value = "users/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ModeratorUserDto>> getAllUser() {
        List<User> users = this.userService.getAll();

        if (users == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ModeratorUserDto.moderatorUserDtoList(users), HttpStatus.OK);
    }
}
