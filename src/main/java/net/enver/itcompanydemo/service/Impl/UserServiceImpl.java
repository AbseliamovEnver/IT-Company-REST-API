package net.enver.itcompanydemo.service.Impl;

import lombok.extern.slf4j.Slf4j;
import net.enver.itcompanydemo.model.*;
import net.enver.itcompanydemo.repository.DepartmentRepository;
import net.enver.itcompanydemo.repository.PositionRepository;
import net.enver.itcompanydemo.repository.RoleRepository;
import net.enver.itcompanydemo.repository.UserRepository;
import net.enver.itcompanydemo.service.UserService;
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

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, DepartmentRepository departmentRepository, PositionRepository positionRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.departmentRepository = departmentRepository;
        this.positionRepository = positionRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void save(User user) {
        Set<Role> roles = new HashSet<>();
        Set<Department> departments = new HashSet<>();

        for (Department department : user.getDepartments()) {
            departments.add(departmentRepository.findByName(department.getName()));
        }
        roles.add(roleRepository.findByName("ROLE_USER"));

        if (user.getStatus() == null) {
            user.setStatus(Status.PROBATION);
        }

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(roles);
        user.setDepartments(departments);
        userRepository.save(user);

        log.info("In UserServiceImpl method save: {} successfully saved", user);
    }

    @Override
    public void update(Long id, User user) {
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
        Status status = user.getStatus();
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
        if (status != null) {
            updatedUser.setStatus(status);
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
        log.info("In UserServiceImpl method getById {}", id);
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User findByUsername(String username) {
        log.info("In UserServiceImpl method findByUsername {}", username);
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByPhoneNumber(String phoneNumber) {
        log.info("In UserServiceImpl method findByPhoneNumber {}", phoneNumber);
        return userRepository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public List<User> getAll() {
        log.info("In UserServiceImpl method getAll");
        return userRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
        log.info("In UserServiceImpl method delete {}", id);
    }
}
