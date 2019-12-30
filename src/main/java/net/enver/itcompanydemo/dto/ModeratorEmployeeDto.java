package net.enver.itcompanydemo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import net.enver.itcompanydemo.model.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ModeratorEmployeeDto {

    private String firstName;
    private String lastName;
    private BigDecimal salary;
    private Date birthday;
    private Date hiredDay;
    private String phoneNumber;
    private EmployeeStatus employeeStatus;
    private Set<Department> departments;
    private Set<Position> positions;

    public static ModeratorEmployeeDto fromEmployee(Employee employee) {
        ModeratorEmployeeDto moderatorEmployeeDto = new ModeratorEmployeeDto();

        moderatorEmployeeDto.setFirstName(employee.getFirstName());
        moderatorEmployeeDto.setLastName(employee.getLastName());
        moderatorEmployeeDto.setSalary(employee.getSalary());
        moderatorEmployeeDto.setBirthday(employee.getBirthday());
        moderatorEmployeeDto.setHiredDay(employee.getHiredDay());
        moderatorEmployeeDto.setPhoneNumber(employee.getUser().getPhoneNumber());
        moderatorEmployeeDto.setEmployeeStatus(employee.getEmployeeStatus());
        moderatorEmployeeDto.setDepartments(employee.getDepartments());
        moderatorEmployeeDto.setPositions(employee.getPositions());

        return moderatorEmployeeDto;
    }

    public static List<ModeratorEmployeeDto> moderatorEmployeeDtoList(List<Employee> employees) {
        List<ModeratorEmployeeDto> employeeDtoList = new ArrayList<>();
        employees.forEach(employee -> employeeDtoList.add(fromEmployee(employee)));
        return employeeDtoList;
    }
}
