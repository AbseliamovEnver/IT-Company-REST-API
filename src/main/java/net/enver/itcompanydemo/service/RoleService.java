package net.enver.itcompanydemo.service;

import net.enver.itcompanydemo.model.Role;

public interface RoleService extends BaseService<Role> {

    Role findByName(String name);
}
