package net.enver.itcompanydemo.service.Impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.enver.itcompanydemo.model.Position;
import net.enver.itcompanydemo.repository.PositionRepository;
import net.enver.itcompanydemo.service.PositionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class PositionServiceImpl implements PositionService {

    private final PositionRepository positionRepository;

    @Override
    public void save(Position position) {
        positionRepository.save(position);
        log.info("In PositionServiceImpl method save: {} successfully saved", position);
    }

    @Override
    public Position getById(Long id) {
        log.info("In PositionServiceImpl method getById {}", id);
        return positionRepository.findById(id).orElse(null);
    }

    @Override
    public Position findByName(String positionName) {
        log.info("In PositionServiceImpl method findByName {}", positionName);
        return positionRepository.findByName(positionName);
    }

    @Override
    public List<Position> getAll() {
        log.info("In PositionServiceImpl method getAll");
        return positionRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        positionRepository.deleteById(id);
        log.info("In PositionServiceImpl method delete {}", id);
    }
}
