package net.enver.itcompanydemo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import net.enver.itcompanydemo.model.Department;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DepartmentDto {
    private Long id;
    private String name;

    public static DepartmentDto fromDepartment(Department department) {
        DepartmentDto departmentDto = new DepartmentDto();

        departmentDto.setId(department.getId());
        departmentDto.setName(department.getName());
        return departmentDto;
    }

    public static List<DepartmentDto> departmentDtoList(List<Department> departments) {
        List<DepartmentDto> departmentList = new ArrayList<>();
        departments.forEach(department -> departmentList.add(fromDepartment(department)));
        return departmentList;
    }
}
