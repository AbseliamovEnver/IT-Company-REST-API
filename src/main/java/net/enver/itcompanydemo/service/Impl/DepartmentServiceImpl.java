package net.enver.itcompanydemo.service.Impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.enver.itcompanydemo.model.Department;
import net.enver.itcompanydemo.repository.DepartmentRepository;
import net.enver.itcompanydemo.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public void save(Department department) {
        departmentRepository.save(department);
        log.info("In DepartmentServiceImpl method save: {} successfully saved", department);
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
