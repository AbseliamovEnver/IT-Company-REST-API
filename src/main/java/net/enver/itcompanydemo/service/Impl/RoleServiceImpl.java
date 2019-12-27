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
        roleRepository.save(role);
        log.info("In RoleServiceImpl method save: {} successfully saved", role);
    }

    @Override
    public void update(Long id, Role role) {
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
        log.info("In RoleServiceImpl method update: {} successfully updated", updatedRole);
    }

    @Override
    public Role getById(Long id) {
        log.info("In RoleServiceImpl method getById {}", id);
        return roleRepository.findById(id).orElse(null);
    }

    @Override
    public Role findByName(String roleName) {
        log.info("In RoleServiceImpl method findByName {}", roleName);
        return roleRepository.findByName(roleName);
    }

    @Override
    public List<Role> getAll() {
        log.info("In RoleServiceImpl method getAll");
        return roleRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        roleRepository.deleteById(id);
        log.info("In RoleServiceImpl method delete {}", id);
    }
}
