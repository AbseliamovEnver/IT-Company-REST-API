package net.enver.itcompanydemo.rest.moderator;

import net.enver.itcompanydemo.dto.moderator.ModeratorUserDto;
import net.enver.itcompanydemo.model.User;
import net.enver.itcompanydemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/v1/moderator/users/")
public class ModeratorUserRestControllerV1 {

    private final UserService userService;

    @Autowired
    public ModeratorUserRestControllerV1(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ModeratorUserDto> getUserById(@PathVariable Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User user = this.userService.getById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ModeratorUserDto.fromUser(user), HttpStatus.OK);
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> saveUser(@RequestBody @Valid User user) {
        HttpHeaders headers = new HttpHeaders();

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.userService.save(user);
        return new ResponseEntity<>(user, headers, HttpStatus.CREATED);
    }

    @PutMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateUser(@PathVariable @NotNull Long id,
                                           @RequestBody @Valid User user, UriComponentsBuilder builder) {
        HttpHeaders headers = new HttpHeaders();

        if (id == null || user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.userService.update(id, user);
        return new ResponseEntity<>(user, headers, HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> deleteUser(@PathVariable @NotNull Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User user = this.userService.getById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ModeratorUserDto>> getAllUser() {
        List<User> users = this.userService.getAll();

        if (users == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ModeratorUserDto.moderatorUserDtoList(users), HttpStatus.OK);
    }
}
