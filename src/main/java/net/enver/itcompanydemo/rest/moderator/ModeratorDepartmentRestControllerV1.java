package net.enver.itcompanydemo.rest.moderator;

import net.enver.itcompanydemo.dto.moderator.ModeratorDepartmentDto;
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
@RequestMapping("/api/v1/moderator/departments/")
public class ModeratorDepartmentRestControllerV1 {

    private final DepartmentService departmentService;

    @Autowired
    public ModeratorDepartmentRestControllerV1(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ModeratorDepartmentDto> getDepartmentById(@PathVariable @NotNull Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Department department = this.departmentService.getById(id);

        if (department == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ModeratorDepartmentDto.fromDepartment(department), HttpStatus.OK);
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
    public ResponseEntity<List<ModeratorDepartmentDto>> getAllDepartment() {
        List<Department> departments = this.departmentService.getAll();

        if (departments == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ModeratorDepartmentDto.departmentDtoList(departments), HttpStatus.OK);
    }
}
