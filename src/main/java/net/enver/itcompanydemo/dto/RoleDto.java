package net.enver.itcompanydemo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import net.enver.itcompanydemo.model.Role;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoleDto {
    private Long id;
    private String name;

    public static RoleDto fromRole(Role role) {
        RoleDto roleDto = new RoleDto();

        roleDto.setId(role.getId());
        roleDto.setName(role.getName());
        return roleDto;
    }

    public static List<RoleDto> roleDtoList(List<Role> roles) {
        List<RoleDto> roleList = new ArrayList<>();
        roles.forEach(role -> roleList.add(fromRole(role)));
        return roleList;
    }
}
