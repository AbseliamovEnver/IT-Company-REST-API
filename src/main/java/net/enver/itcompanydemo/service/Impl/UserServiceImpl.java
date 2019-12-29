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
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final DepartmentRepository departmentRepository;
    private final PositionRepository positionRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, DepartmentRepository departmentRepository, PositionRepository positionRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.departmentRepository = departmentRepository;
        this.positionRepository = positionRepository;
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
        userRepository.save(user);

        log.info("In UserServiceImpl method save: {} successfully saved", user);
    }

    @Override
    public void update(Long id, User user) {
        log.info("In UserServiceImpl method update.");

        Set<Role> userRoles = new HashSet<>();
        Set<Department> userDepartments = new HashSet<>();
        Set<Position> userPositions = new HashSet<>();

        User updatedUser = getById(id);

        String username = user.getUsername();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String password = user.getPassword();
        String phoneNumber = user.getPhoneNumber();
        BigDecimal salary = user.getSalary();
        Date birthday = user.getBirthday();
        Date hiredDay = user.getHiredDay();
        EmployeeStatus employeeStatus = user.getEmployeeStatus();
        Set<Role> roles = user.getRoles();
        Set<Department> departments = user.getDepartments();
        Set<Position> positions = user.getPositions();

        if (username != null) {
            updatedUser.setUsername(username);
        }
        if (firstName != null) {
            updatedUser.setFirstName(firstName);
        }
        if (lastName != null) {
            updatedUser.setLastName(lastName);
        }
        if (password != null) {
            updatedUser.setPassword(bCryptPasswordEncoder.encode(password));
        }
        if (phoneNumber != null) {
            updatedUser.setPhoneNumber(phoneNumber);
        }
        if (salary != null) {
            updatedUser.setSalary(salary);
        }
        if (birthday != null) {
            updatedUser.setBirthday(birthday);
        }
        if (hiredDay != null) {
            updatedUser.setHiredDay(hiredDay);
        }
        if (employeeStatus != null) {
            updatedUser.setEmployeeStatus(employeeStatus);
        }
        if (roles != null) {
            for (Role role : roles) {
                userRoles.add(roleRepository.findByName(role.getName()));
            }
            updatedUser.setRoles(userRoles);
        }
        if (departments != null) {
            for (Department department : departments) {
                userDepartments.add(departmentRepository.findByName(department.getName()));
            }
            updatedUser.setDepartments(userDepartments);
        }
        if (positions != null) {
            for (Position position : positions) {
                userPositions.add(positionRepository.findByName(position.getName()));
            }
            updatedUser.setPositions(userPositions);
        }
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

        log.info("User list found successfully.");
        return users;
    }

    @Override
    public void delete(Long id) {
        log.info("In UserServiceImpl method delete.");

        userRepository.deleteById(id);

        log.info("User with ID {} successfully deleted.", id);
    }

    @Override
    public User register(User user) {
        log.info("In UserServiceImpl method register.");

        return null;
    }

    @Override
    public void activate(User user) {
        log.info("In UserServiceImpl method activate.");

        userRepository.save(user);
    }
}
