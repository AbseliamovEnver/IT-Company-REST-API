package net.enver.itcompanydemo.service;

import net.enver.itcompanydemo.model.Department;

import java.util.List;

public interface DepartmentService {

    void save(Department department);

    Department getById(Long id);

    void delete(Long id);

    List<Department> getAll();

    Department findByName(String departmentName);
}
