package net.enver.itcompanydemo.rest;

import net.enver.itcompanydemo.dto.RoleDto;
import net.enver.itcompanydemo.model.Role;
import net.enver.itcompanydemo.service.RoleService;
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
@RequestMapping("/api/v1/roles/")
public class RoleRestControllerV1 {

    private final RoleService roleService;

    @Autowired
    public RoleRestControllerV1(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RoleDto> getRoleById(@PathVariable @NotNull Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Role role = this.roleService.getById(id);

        if (role == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(RoleDto.fromRole(role), HttpStatus.OK);
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Role> saveRole(@RequestBody @Valid Role role) {
        HttpHeaders headers = new HttpHeaders();

        if (role == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.roleService.save(role);
        return new ResponseEntity<>(role, headers, HttpStatus.CREATED);
    }

    @PutMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Role> updateRole(@PathVariable @NotNull Long id,
                                           @RequestBody @Valid Role role, UriComponentsBuilder builder) {
        HttpHeaders headers = new HttpHeaders();

        if (id == null || role == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.roleService.update(id, role);
        return new ResponseEntity<>(role, headers, HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Role> deleteRole(@PathVariable @NotNull Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Role role = this.roleService.getById(id);

        if (role == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.roleService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RoleDto>> getAllRoles() {
        List<Role> roles = this.roleService.getAll();

        if (roles == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(RoleDto.roleDtoList(roles), HttpStatus.OK);
    }
}
