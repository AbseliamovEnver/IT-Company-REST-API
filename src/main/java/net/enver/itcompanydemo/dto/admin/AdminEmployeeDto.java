package net.enver.itcompanydemo.dto.admin;

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
public class AdminEmployeeDto {

    private Long id;
    private String username;
    private String phoneNumber;
    private UserStatus userStatus;
    private Set<Role> roles;
    private Date createdDate;
    private Date lastModifiedDate;
    private String firstName;
    private String lastName;
    private BigDecimal salary;
    private Date birthday;
    private Date hiredDay;
    private EmployeeStatus employeeStatus;
    private Set<Department> departments;
    private Set<Position> positions;

    public Employee toEmployee() {
        Employee employee = new Employee();

        employee.setId(id);
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setSalary(salary);
        employee.setBirthday(birthday);
        employee.setHiredDay(hiredDay);
        employee.setEmployeeStatus(employeeStatus);
        employee.setDepartments(departments);
        employee.setPositions(positions);

        return employee;
    }

    public static AdminEmployeeDto fromEmployee(Employee employee) {
        AdminEmployeeDto adminEmployeeDto = new AdminEmployeeDto();

        adminEmployeeDto.setId(employee.getId());
        adminEmployeeDto.setUsername(employee.getUser().getUsername());
        adminEmployeeDto.setUsername(employee.getUser().getPhoneNumber());
        adminEmployeeDto.setRoles(employee.getUser().getRoles());
        adminEmployeeDto.setFirstName(employee.getFirstName());
        adminEmployeeDto.setLastName(employee.getLastName());
        adminEmployeeDto.setSalary(employee.getSalary());
        adminEmployeeDto.setBirthday(employee.getBirthday());
        adminEmployeeDto.setHiredDay(employee.getHiredDay());
        adminEmployeeDto.setDepartments(employee.getDepartments());
        adminEmployeeDto.setPositions(employee.getPositions());
        adminEmployeeDto.setUserStatus(employee.getUser().getUserStatus());
        adminEmployeeDto.setEmployeeStatus(employee.getEmployeeStatus());
        adminEmployeeDto.setCreatedDate(employee.getUser().getCreatedDate());
        adminEmployeeDto.setLastModifiedDate(employee.getUser().getLastModifiedDate());

        return adminEmployeeDto;
    }

    public static List<AdminEmployeeDto> adminEmployeeDtoList(List<Employee> employees) {
        List<AdminEmployeeDto> employeeList = new ArrayList<>();
        employees.forEach(employee -> employeeList.add(fromEmployee(employee)));
        return employeeList;
    }
}
