package net.enver.itcompanydemo.service.Impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.enver.itcompanydemo.model.Role;
import net.enver.itcompanydemo.repository.RoleRepository;
import net.enver.itcompanydemo.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public void save(Role role) {
        roleRepository.save(role);
        log.info("In RoleServiceImpl method save: {} successfully saved", role);
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
