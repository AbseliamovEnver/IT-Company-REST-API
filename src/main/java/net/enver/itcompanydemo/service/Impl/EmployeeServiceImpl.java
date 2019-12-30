package net.enver.itcompanydemo.service.Impl;

import lombok.extern.slf4j.Slf4j;
import net.enver.itcompanydemo.model.*;
import net.enver.itcompanydemo.repository.DepartmentRepository;
import net.enver.itcompanydemo.repository.EmployeeRepository;
import net.enver.itcompanydemo.repository.PositionRepository;
import net.enver.itcompanydemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final PositionRepository positionRepository;
    private Calendar calendar = Calendar.getInstance();

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               DepartmentRepository departmentRepository, PositionRepository positionRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.positionRepository = positionRepository;
    }

    @Override
    public void save(Employee employee) {
        log.info("In EmployeeServiceImpl method save.");

        Set<Department> departments = new HashSet<>();
        Set<Position> positions = new HashSet<>();

        if (employee.getDepartments() == null) {
            departments.add(departmentRepository.getOne(1L));
        } else {
            for (Department department : employee.getDepartments()) {
                departments.add(departmentRepository.findByName(department.getName()));
            }
        }
        if (employee.getPositions() == null) {
            positions.add(positionRepository.getOne(1L));
        } else {
            for (Position position : employee.getPositions()) {
                positions.add(positionRepository.findByName(position.getName()));
            }
        }
        if (employee.getEmployeeStatus() == null) {
            employee.setEmployeeStatus(EmployeeStatus.PROBATION);
        }

        employee.setDepartments(departments);
        employee.setPositions(positions);
        employee.getUser().setLastModifiedDate(calendar.getTime());
        employeeRepository.save(employee);

        log.info("In EmployeeServiceImpl method save: {} successfully saved", employee);
    }

    @Override
    public void update(Long id, Employee employee) {
        log.info("In EmployeeServiceImpl method update.");

        Set<Department> employeeDepartments = new HashSet<>();
        Set<Position> employeePositions = new HashSet<>();

        Employee updateEmployee = getById(id);
        String firstName = employee.getFirstName();
        String lastName = employee.getLastName();
        BigDecimal salary = employee.getSalary();
        Date birthday = employee.getBirthday();
        Date hiredDay = employee.getHiredDay();
        EmployeeStatus employeeStatus = employee.getEmployeeStatus();
        Set<Department> departments = employee.getDepartments();
        Set<Position> positions = employee.getPositions();

        if (firstName != null) {
            updateEmployee.setFirstName(firstName);
        }
        if (lastName != null) {
            updateEmployee.setLastName(lastName);
        }
        if (salary != null) {
            updateEmployee.setSalary(salary);
        }
        if (birthday != null) {
            updateEmployee.setBirthday(birthday);
        }
        if (hiredDay != null) {
            updateEmployee.setHiredDay(hiredDay);
        }
        if (employeeStatus != null) {
            updateEmployee.setEmployeeStatus(employeeStatus);
        }
        if (departments != null) {
            for (Department department : departments) {
                employeeDepartments.add(departmentRepository.findByName(department.getName()));
            }
            updateEmployee.setDepartments(employeeDepartments);
        }
        if (positions != null) {
            for (Position position : positions) {
                employeePositions.add(positionRepository.findByName(position.getName()));
            }
            updateEmployee.setPositions(employeePositions);
        }
        employee.getUser().setLastModifiedDate(calendar.getTime());
        employeeRepository.save(updateEmployee);

        log.info("In EmployeeServiceImpl method update: {} successfully updated", updateEmployee);
    }

    @Override
    public Employee getById(Long id) {
        log.info("In EmployeeServiceImpl method getById.");

        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee != null) {
            log.info("Employee with ID {} found successfully.", id);
        } else {
            log.info("Employee with ID {} not found.", id);
        }
        return employee;
    }

    @Override
    public void delete(Long id) {
        log.info("In EmployeeServiceImpl method delete.");

        Employee employee = getById(id);
        employee.setEmployeeStatus(EmployeeStatus.FIRED);
        employee.getUser().setUserStatus(UserStatus.DELETED);
        employee.getUser().setLastModifiedDate(calendar.getTime());
        employeeRepository.deleteById(id);

        log.info("Employee with ID {} successfully deleted.", id);
    }

    @Override
    public List<Employee> getAll() {
        log.info("In EmployeeServiceImpl method getAll.");

        List<Employee> employees = employeeRepository.findAll();

        log.info("Employee list found successfully.");
        return employees;
    }
}
