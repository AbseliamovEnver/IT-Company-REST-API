package net.enver.itcompanydemo.rest.employee;

import net.enver.itcompanydemo.dto.employee.EmployeeDto;
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
@RequestMapping("/api/v1/employees/")
public class EmployeeRestControllerV1 {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeRestControllerV1(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable @NotNull Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Employee employee = this.employeeService.getById(id);

        if (employee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(EmployeeDto.fromEmployee(employee), HttpStatus.OK);
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EmployeeDto>> getAllEmployee() {
        List<Employee> employees = this.employeeService.getAll();

        if (employees == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(EmployeeDto.employeeDtoList(employees), HttpStatus.OK);
    }
}
