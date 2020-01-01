package net.enver.itcompanydemo.dto.admin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import net.enver.itcompanydemo.model.Role;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdminRoleDto {
    private Long id;
    private String name;

    public static AdminRoleDto fromRole(Role role) {
        AdminRoleDto adminRoleDto = new AdminRoleDto();

        adminRoleDto.setId(role.getId());
        adminRoleDto.setName(role.getName());
        return adminRoleDto;
    }

    public static List<AdminRoleDto> roleDtoList(List<Role> roles) {
        List<AdminRoleDto> roleList = new ArrayList<>();
        roles.forEach(role -> roleList.add(fromRole(role)));
        return roleList;
    }
}
