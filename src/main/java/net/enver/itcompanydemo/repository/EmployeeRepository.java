package net.enver.itcompanydemo.repository;


import net.enver.itcompanydemo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for {@link Employee} class.
 */

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
