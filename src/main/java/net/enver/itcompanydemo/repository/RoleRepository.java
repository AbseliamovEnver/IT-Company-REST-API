package net.enver.itcompanydemo.repository;

import net.enver.itcompanydemo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for {@link Role} class.
 *
 * @author Enver on 12.12.2019 19:55.
 * @project ITCompanyDemo
 */

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
