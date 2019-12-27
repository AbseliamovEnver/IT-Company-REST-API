package net.enver.itcompanydemo.service.Impl;

import lombok.extern.slf4j.Slf4j;
import net.enver.itcompanydemo.model.Department;
import net.enver.itcompanydemo.model.User;
import net.enver.itcompanydemo.repository.DepartmentRepository;
import net.enver.itcompanydemo.repository.UserRepository;
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
    private final UserRepository userRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository, UserRepository userRepository) {
        this.departmentRepository = departmentRepository;
        this.userRepository = userRepository;
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

        Set<User> userDepartments = new HashSet<>();

        Set<User> users = department.getUsers();
        Department updatedDepartment = departmentRepository.getOne(id);

        if (department.getName() != null) {
            updatedDepartment.setName(department.getName());
        }
        if (users != null) {
            for (User user : users) {
                userDepartments.add(userRepository.findByUsername(user.getUsername()));
            }
            updatedDepartment.setUsers(userDepartments);
        }
        departmentRepository.save(updatedDepartment);

        log.info("Department with ID {} successfully updated.", updatedDepartment);
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
        log.info("In DepartmentServiceImpl method findByName.");

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
