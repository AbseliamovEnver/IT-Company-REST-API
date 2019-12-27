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
        departmentRepository.save(department);
        log.info("In DepartmentServiceImpl method save: {} successfully saved", department);
    }

    @Override
    public void update(Long id, Department department) {
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
        log.info("In DepartmentServiceImpl method update: {} successfully updated", updatedDepartment);
    }

    @Override
    public Department getById(Long id) {
        log.info("In DepartmentServiceImpl method getById {}", id);
        return departmentRepository.findById(id).orElse(null);
    }

    @Override
    public Department findByName(String departmentName) {
        log.info("In DepartmentServiceImpl method findByName {}", departmentName);
        return departmentRepository.findByName(departmentName);
    }

    @Override
    public List<Department> getAll() {
        log.info("In DepartmentServiceImpl method getAll");
        return departmentRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        departmentRepository.deleteById(id);
        log.info("In DepartmentServiceImpl method delete {}", id);
    }
}
