package net.enver.itcompanydemo.dto.employee;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import net.enver.itcompanydemo.model.Department;
import net.enver.itcompanydemo.model.Employee;
import net.enver.itcompanydemo.model.Position;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeDto {

    private String username;
    private String firstName;
    private String lastName;
    private Date birthday;
    private Set<Department> departments;
    private Set<Position> positions;

    public static EmployeeDto fromEmployee(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();

        employeeDto.setUsername(employee.getUser().getUsername());
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setBirthday(employee.getBirthday());
        employeeDto.setDepartments(employee.getDepartments());
        employeeDto.setPositions(employee.getPositions());

        return employeeDto;
    }

    public static List<EmployeeDto> employeeDtoList(List<Employee> employees) {
        List<EmployeeDto> employeeList = new ArrayList<>();
        employees.forEach(employee -> employeeList.add(fromEmployee(employee)));
        return employeeList;
    }
}
