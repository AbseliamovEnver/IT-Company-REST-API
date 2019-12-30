package net.enver.itcompanydemo.service.Impl;

import lombok.extern.slf4j.Slf4j;
import net.enver.itcompanydemo.model.Department;
import net.enver.itcompanydemo.model.Employee;
import net.enver.itcompanydemo.repository.DepartmentRepository;
import net.enver.itcompanydemo.repository.EmployeeRepository;
import net.enver.itcompanydemo.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void save(Department department) {
        log.info("In DepartmentServiceImpl method save.");

        departmentRepository.save(department);

        log.info("Department {} successfully saved.", department);
    }

    @Override
    public void update(Long id, Department department) {
        log.info("In DepartmentServiceImpl method update.");

        Employee findEmployee;
        Set<Employee> employeeDepartments = new HashSet<>();

        Department updatedDepartment = getById(id);
        Set<Employee> employees = department.getEmployees();

        if (updatedDepartment != null) {
            if (department.getName() != null) {
                updatedDepartment.setName(department.getName());
            }
            if (employees != null) {
                for (Employee employee : employees) {
                    if ((findEmployee = employeeRepository.findById(employee.getId()).orElse(null)) != null) {
                        employeeDepartments.add(findEmployee);
                    }
                }
                updatedDepartment.setEmployees(employeeDepartments);
            }
            departmentRepository.save(updatedDepartment);

            log.info("Department with ID {} successfully updated.", updatedDepartment);
        }
    }

    @Override
    public Department getById(Long id) {
        log.info("In DepartmentServiceImpl method getById.");

        Department department = departmentRepository.findById(id).orElse(null);
        if (department != null) {
            log.info("Department with ID {} found successfully.", id);
        } else {
            log.info("Department with ID {} not found.", id);
        }
        return department;
    }

    @Override
    public Department findByName(String departmentName) {
        log.info("In DepartmentServiceImpl method findById.");

        Department department = departmentRepository.findByName(departmentName);
        if (department != null) {
            log.info("Department with name {} found successfully.", departmentName);
        } else {
            log.info("Department with name {} not found.", departmentName);
        }
        return department;
    }

    @Override
    public List<Department> getAll() {
        log.info("In DepartmentServiceImpl method getAll.");

        List<Department> departments = departmentRepository.findAll();

        log.info("Department list found successfully.");
        return departments;
    }

    @Override
    public void delete(Long id) {
        log.info("In DepartmentServiceImpl method delete.");

        departmentRepository.deleteById(id);

        log.info("Department with ID {} successfully deleted.", id);
    }
}
