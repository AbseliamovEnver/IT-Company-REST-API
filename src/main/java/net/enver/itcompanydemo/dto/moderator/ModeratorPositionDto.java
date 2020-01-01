package net.enver.itcompanydemo.dto.moderator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import net.enver.itcompanydemo.model.Position;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ModeratorPositionDto {
    private Long id;
    private String name;

    public static ModeratorPositionDto fromPosition(Position position) {
        ModeratorPositionDto moderatorPositionDto = new ModeratorPositionDto();

        moderatorPositionDto.setId(position.getId());
        moderatorPositionDto.setName(position.getName());
        return moderatorPositionDto;
    }

    public static List<ModeratorPositionDto> positionDtoList(List<Position> positions) {
        List<ModeratorPositionDto> positionList = new ArrayList<>();
        positions.forEach(position -> positionList.add(fromPosition(position)));
        return positionList;
    }
}
