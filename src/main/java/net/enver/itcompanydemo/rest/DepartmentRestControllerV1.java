package net.enver.itcompanydemo.rest;

import net.enver.itcompanydemo.dto.DepartmentDto;
import net.enver.itcompanydemo.model.Department;
import net.enver.itcompanydemo.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/v1/departments/")
public class DepartmentRestControllerV1 {
    private final DepartmentService departmentService;

    @Autowired
    public DepartmentRestControllerV1(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable @NotNull Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Department department = this.departmentService.getById(id);

        if (department == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(DepartmentDto.fromDepartment(department), HttpStatus.OK);
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Department> saveDepartment(@RequestBody @Valid Department department) {
        HttpHeaders headers = new HttpHeaders();

        if (department == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.departmentService.save(department);
        return new ResponseEntity<>(department, headers, HttpStatus.CREATED);
    }

    @PutMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Department> updateDepartment(@PathVariable @NotNull Long id,
                                                       @RequestBody @Valid Department department, UriComponentsBuilder builder) {
        HttpHeaders headers = new HttpHeaders();

        if (id == null || department == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.departmentService.update(id, department);
        return new ResponseEntity<>(department, headers, HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Department> deleteDepartment(@PathVariable @NotNull Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Department department = this.departmentService.getById(id);

        if (department == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.departmentService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DepartmentDto>> getAllDepartment() {
        List<Department> departments = this.departmentService.getAll();

        if (departments == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(DepartmentDto.departmentDtoList(departments), HttpStatus.OK);
    }
}
