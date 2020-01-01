package net.enver.itcompanydemo.rest.admin;

import net.enver.itcompanydemo.dto.admin.AdminEmployeeDto;
import net.enver.itcompanydemo.model.Employee;
import net.enver.itcompanydemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/employees/")
public class AdminEmployeeRestControllerV1 {

    private final EmployeeService employeeService;

    @Autowired
    public AdminEmployeeRestControllerV1(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AdminEmployeeDto> getEmployeeById(@PathVariable @NotNull Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Employee employee = this.employeeService.getById(id);

        if (employee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(AdminEmployeeDto.fromEmployee(employee), HttpStatus.OK);
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AdminEmployeeDto>> getAllEmployee() {
        List<Employee> employees = this.employeeService.getAll();

        if (employees == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(AdminEmployeeDto.adminEmployeeDtoList(employees), HttpStatus.OK);
    }
}
