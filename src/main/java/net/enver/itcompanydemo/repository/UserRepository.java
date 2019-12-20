package net.enver.itcompanydemo.repository;

import net.enver.itcompanydemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for {@link User} class.
 *
 * @author Enver on 12.12.2019 19:55.
 * @project ITCompanyDemo
 */

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
