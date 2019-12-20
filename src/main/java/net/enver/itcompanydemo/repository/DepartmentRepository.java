package net.enver.itcompanydemo.repository;

import net.enver.itcompanydemo.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for {@link Department} class.
 *
 * @author Enver on 12.12.2019 19:58.
 * @project ITCompanyDemo
 */

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Department findByName(String name);
}
