package net.enver.itcompanydemo.repository;

import net.enver.itcompanydemo.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for {@link Position} class.
 *
 * @author Enver on 16.12.2019 21:33.
 * @project ITCompanyDemo
 */
public interface PositionRepository extends JpaRepository<Position, Long> {
    Position findByName(String name);
}
