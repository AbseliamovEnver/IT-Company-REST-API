package net.enver.itcompanydemo.service;

import net.enver.itcompanydemo.model.Position;

import java.util.List;

public interface PositionService {

    void save(Position position);

    void update(Long id, Position position);

    Position getById(Long id);

    void delete(Long id);

    List<Position> getAll();

    Position findByName(String positionName);
}
