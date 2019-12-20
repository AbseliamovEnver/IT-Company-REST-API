package net.enver.itcompanydemo.service;

import net.enver.itcompanydemo.model.Role;

import java.util.List;

public interface RoleService {

    void save(Role role);

    Role getById(Long id);

    void delete(Long id);

    List<Role> getAll();

    Role findByName(String roleName);
}
