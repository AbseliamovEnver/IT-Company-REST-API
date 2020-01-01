package net.enver.itcompanydemo.dto.moderator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import net.enver.itcompanydemo.model.Department;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ModeratorDepartmentDto {
    private Long id;
    private String name;

    public static ModeratorDepartmentDto fromDepartment(Department department) {
        ModeratorDepartmentDto moderatorDepartmentDto = new ModeratorDepartmentDto();

        moderatorDepartmentDto.setId(department.getId());
        moderatorDepartmentDto.setName(department.getName());
        return moderatorDepartmentDto;
    }

    public static List<ModeratorDepartmentDto> departmentDtoList(List<Department> departments) {
        List<ModeratorDepartmentDto> departmentList = new ArrayList<>();
        departments.forEach(department -> departmentList.add(fromDepartment(department)));
        return departmentList;
    }
}
