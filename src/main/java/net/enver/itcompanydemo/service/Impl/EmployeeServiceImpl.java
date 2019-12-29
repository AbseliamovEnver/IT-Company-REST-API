package net.enver.itcompanydemo.service.Impl;

import lombok.extern.slf4j.Slf4j;
import net.enver.itcompanydemo.model.*;
import net.enver.itcompanydemo.repository.DepartmentRepository;
import net.enver.itcompanydemo.repository.EmployeeRepository;
import net.enver.itcompanydemo.repository.PositionRepository;
import net.enver.itcompanydemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final PositionRepository positionRepository;

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

        Set<Role> roles = new HashSet<>();
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
    }

    @Override
    public void update(Long id, Employee employee) {

    }

    @Override
    public Employee getById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Employee> getAll() {
        return null;
    }
}
