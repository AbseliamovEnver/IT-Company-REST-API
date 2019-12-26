package net.enver.itcompanydemo.repository;

import net.enver.itcompanydemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for {@link User} class.
 */

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByPhoneNumber(String phoneNumber);
}
