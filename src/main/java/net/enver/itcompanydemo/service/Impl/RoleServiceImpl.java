package net.enver.itcompanydemo.service.Impl;

import lombok.extern.slf4j.Slf4j;
import net.enver.itcompanydemo.model.Role;
import net.enver.itcompanydemo.model.User;
import net.enver.itcompanydemo.repository.RoleRepository;
import net.enver.itcompanydemo.repository.UserRepository;
import net.enver.itcompanydemo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void save(Role role) {
        log.info("In RoleServiceImpl method save.");

        roleRepository.save(role);

        log.info("Role {} successfully saved.", role);
    }

    @Override
    public void update(Long id, Role role) {
        log.info("In RoleServiceImpl method update.");

        Set<User> userRoles = new HashSet<>();

        Set<User> users = role.getUsers();
        Role updatedRole = roleRepository.getOne(id);

        if (role.getName() != null) {
            updatedRole.setName(role.getName());
        }
        if (users != null) {
            for (User user : users) {
                userRoles.add(userRepository.findByUsername(user.getUsername()));
            }
            updatedRole.setUsers(userRoles);
        }
        roleRepository.save(updatedRole);

        log.info("Role with ID {} successfully updated.", updatedRole);
    }

    @Override
    public Role getById(Long id) {
        log.info("In RoleServiceImpl method getById.");

        Role role = roleRepository.findById(id).orElse(null);
        if (role != null) {
            log.info("Role with ID {} found successfully.", id);
        } else {
            log.info("Role with ID {} not found.", id);
        }
        return role;
    }

    @Override
    public Role findByName(String roleName) {
        log.info("In RoleServiceImpl method findByName.");

        Role role = roleRepository.findByName(roleName);
        if (role != null) {
            log.info("Role with name {} found successfully.", roleName);
        } else {
            log.info("Role with name {} not found.", roleName);
        }
        return role;
    }

    @Override
    public List<Role> getAll() {
        log.info("In RoleServiceImpl method getAll.");

        List<Role> roles = roleRepository.findAll();

        log.info("Role list found successfully.");
        return roles;
    }

    @Override
    public void delete(Long id) {
        log.info("In RoleServiceImpl method delete.");

        roleRepository.deleteById(id);

        log.info("Role with ID {} successfully deleted.", id);
    }
}
