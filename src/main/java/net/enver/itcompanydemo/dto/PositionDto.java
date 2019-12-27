package net.enver.itcompanydemo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import net.enver.itcompanydemo.model.Position;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PositionDto {
    private Long id;
    private String name;

    public static PositionDto fromPosition(Position position) {
        PositionDto positionDto = new PositionDto();

        positionDto.setId(position.getId());
        positionDto.setName(position.getName());
        return positionDto;
    }

    public static List<PositionDto> positionDtoList(List<Position> positions) {
        List<PositionDto> positionList = new ArrayList<>();
        positions.forEach(position -> positionList.add(fromPosition(position)));
        return positionList;
    }
}
