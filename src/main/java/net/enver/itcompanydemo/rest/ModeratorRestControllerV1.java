package net.enver.itcompanydemo.rest;

import net.enver.itcompanydemo.dto.ModeratorEmployeeDto;
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

import java.util.List;

@RestController
@RequestMapping("/api/v1/moderator/")
public class ModeratorRestControllerV1 {

    private final EmployeeService employeeService;

    @Autowired
    public ModeratorRestControllerV1(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(value = "employee/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ModeratorEmployeeDto> getEmployeeById(@PathVariable("id") Long employeeId) {
        if (employeeId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Employee employee = this.employeeService.getById(employeeId);

        if (employee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ModeratorEmployeeDto.fromEmployee(employee), HttpStatus.OK);
    }

    @GetMapping(value = "employee/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ModeratorEmployeeDto>> getAllEmployee() {
        List<Employee> employees = this.employeeService.getAll();

        if (employees == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ModeratorEmployeeDto.moderatorEmployeeDtoList(employees), HttpStatus.OK);
    }
}
