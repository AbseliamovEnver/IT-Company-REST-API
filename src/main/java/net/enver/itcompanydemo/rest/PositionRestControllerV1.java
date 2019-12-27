package net.enver.itcompanydemo.rest;

import net.enver.itcompanydemo.dto.PositionDto;
import net.enver.itcompanydemo.model.Position;
import net.enver.itcompanydemo.service.PositionService;
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
@RequestMapping("/api/v1/positions/")
public class PositionRestControllerV1 {
    private final PositionService positionService;

    @Autowired
    public PositionRestControllerV1(PositionService positionService) {
        this.positionService = positionService;
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PositionDto> getPositionById(@PathVariable @NotNull Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Position position = this.positionService.getById(id);

        if (position == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(PositionDto.fromPosition(position), HttpStatus.OK);
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Position> savePosition(@RequestBody @Valid Position position) {
        HttpHeaders headers = new HttpHeaders();

        if (position == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.positionService.save(position);
        return new ResponseEntity<>(position, headers, HttpStatus.CREATED);
    }

    @PutMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Position> updatePosition(@PathVariable @NotNull Long id,
                                           @RequestBody @Valid Position position, UriComponentsBuilder builder) {
        HttpHeaders headers = new HttpHeaders();

        if (id == null || position == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.positionService.update(id, position);
        return new ResponseEntity<>(position, headers, HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Position> deletePosition(@PathVariable @NotNull Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Position position = this.positionService.getById(id);

        if (position == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.positionService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PositionDto>> getAllPositions() {
        List<Position> positions = this.positionService.getAll();

        if (positions == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(PositionDto.positionDtoList(positions), HttpStatus.OK);
    }
}
