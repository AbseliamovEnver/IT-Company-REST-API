package net.enver.itcompanydemo.service;

import net.enver.itcompanydemo.model.Department;

public interface DepartmentService extends BaseService<Department> {

    Department findByName(String name);

}
