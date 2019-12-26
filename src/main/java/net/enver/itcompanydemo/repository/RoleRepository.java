package net.enver.itcompanydemo.repository;

import net.enver.itcompanydemo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for {@link Role} class.
 */

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
