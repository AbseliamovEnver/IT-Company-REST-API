package net.enver.itcompanydemo.service;

import net.enver.itcompanydemo.model.Position;

public interface PositionService extends BaseService<Position> {

    Position findByName(String name);
}
