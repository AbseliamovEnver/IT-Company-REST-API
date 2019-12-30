package net.enver.itcompanydemo.service.Impl;

import lombok.extern.slf4j.Slf4j;
import net.enver.itcompanydemo.model.*;
import net.enver.itcompanydemo.repository.DepartmentRepository;
import net.enver.itcompanydemo.repository.PositionRepository;
import net.enver.itcompanydemo.repository.RoleRepository;
import net.enver.itcompanydemo.repository.UserRepository;
import net.enver.itcompanydemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private Calendar calendar = Calendar.getInstance();

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void save(User user) {
        log.info("In UserServiceImpl method save.");

        Set<Role> roles = new HashSet<>();

        if (user.getRoles() == null) {
            roles.add(roleRepository.findByName("ROLE_USER"));
        } else {
            for (Role role : user.getRoles()) {
                roles.add(roleRepository.findByName(role.getName()));
            }
        }

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(roles);
        user.setCreatedDate(calendar.getTime());
        user.setLastModifiedDate(calendar.getTime());
        userRepository.save(user);

        log.info("In UserServiceImpl method save: {} successfully saved", user);
    }

    @Override
    public void update(Long id, User user) {
        log.info("In UserServiceImpl method update.");

        Set<Role> userRoles = new HashSet<>();

        User updatedUser = getById(id);
        String username = user.getUsername();
        String password = user.getPassword();
        String phoneNumber = user.getPhoneNumber();
        Set<Role> roles = user.getRoles();

        if (username != null) {
            updatedUser.setUsername(username);
        }
        if (password != null) {
            updatedUser.setPassword(bCryptPasswordEncoder.encode(password));
        }
        if (phoneNumber != null) {
            updatedUser.setPhoneNumber(phoneNumber);
        }
        if (roles != null) {
            for (Role role : roles) {
                userRoles.add(roleRepository.findByName(role.getName()));
            }
            updatedUser.setRoles(userRoles);
        }
        user.setLastModifiedDate(calendar.getTime());
        userRepository.save(updatedUser);

        log.info("In UserServiceImpl method update: {} successfully updated", updatedUser);
    }

    @Override
    public User getById(Long id) {
        log.info("In UserServiceImpl method getById.");

        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            log.info("User with ID {} found successfully.", id);
        } else {
            log.info("User with ID {} not found.", id);
        }
        return user;
    }

    @Override
    public User findByUsername(String username) {
        log.info("In UserServiceImpl method findByUsername.");

        User user = userRepository.findByUsername(username);
        if (user != null) {
            log.info("User with username {} found successfully.", username);
        } else {
            log.info("User with username {} not found.", username);
        }
        return user;
    }

    @Override
    public User findByPhoneNumber(String phoneNumber) {
        log.info("In UserServiceImpl method findByPhoneNumber.");

        User user = userRepository.findByPhoneNumber(phoneNumber);
        if (user != null) {
            log.info("User with phone number {} found successfully.", phoneNumber);
        } else {
            log.info("User with phone number {} not found.", phoneNumber);
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        log.info("In UserServiceImpl method getAll.");

        List<User> users = userRepository.findAll();

        log.info("User list successfully found.");
        return users;
    }

    @Override
    public void delete(Long id) {
        log.info("In UserServiceImpl method delete.");

        User user = getById(id);
        user.setUserStatus(UserStatus.DELETED);
        user.setLastModifiedDate(calendar.getTime());
        userRepository.deleteById(id);

        log.info("User with ID {} successfully deleted.", id);
    }

    @Override
    public User register(User user) {
        log.info("In UserServiceImpl method register.");

        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName("ROLE_USER"));

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(roles);
        user.setUserStatus(UserStatus.INACTIVE);
        user.setCreatedDate(calendar.getTime());
        user.setLastModifiedDate(calendar.getTime());

        User registeredUser = userRepository.save(user);

        log.info("User {} successfully registered.", registeredUser);
        return registeredUser;
    }

    @Override
    public void activate(User user) {
        log.info("In UserServiceImpl method activate.");

        user.setUserStatus(UserStatus.ACTIVE);
        user.setLastModifiedDate(calendar.getTime());
        userRepository.save(user);

        log.info("User {} activated successfully.", user);
    }
}
