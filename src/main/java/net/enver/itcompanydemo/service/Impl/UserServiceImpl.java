package net.enver.itcompanydemo.service.Impl;

import lombok.extern.slf4j.Slf4j;
import net.enver.itcompanydemo.model.Department;
import net.enver.itcompanydemo.model.Role;
import net.enver.itcompanydemo.model.Status;
import net.enver.itcompanydemo.model.User;
import net.enver.itcompanydemo.repository.DepartmentRepository;
import net.enver.itcompanydemo.repository.RoleRepository;
import net.enver.itcompanydemo.repository.UserRepository;
import net.enver.itcompanydemo.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final DepartmentRepository departmentRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, DepartmentRepository departmentRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.departmentRepository = departmentRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void save(User user) {
        Set<Role> roles = new HashSet<>();
        Set<Department> departments = new HashSet<>();

        roles.add(roleRepository.findByName("USER"));
        departments.add(departmentRepository.findByName("Probation"));

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(roles);
        user.setDepartments(departments);
        user.setStatus(Status.PROBATION);

        userRepository.save(user);

        log.info("In UserServiceImpl method save: {} successfully saved", user);
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
