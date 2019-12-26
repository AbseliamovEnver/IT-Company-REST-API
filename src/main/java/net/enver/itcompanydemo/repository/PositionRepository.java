package net.enver.itcompanydemo.repository;

import net.enver.itcompanydemo.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for {@link Position} class.
 */
public interface PositionRepository extends JpaRepository<Position, Long> {
    Position findByName(String name);
}
