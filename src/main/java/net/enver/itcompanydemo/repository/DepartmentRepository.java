package net.enver.itcompanydemo.repository;

import net.enver.itcompanydemo.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for {@link Department} class.
 */

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Department findByName(String name);
}
