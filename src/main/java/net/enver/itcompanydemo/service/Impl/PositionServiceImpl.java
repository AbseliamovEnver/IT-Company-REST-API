package net.enver.itcompanydemo.service.Impl;

import lombok.extern.slf4j.Slf4j;
import net.enver.itcompanydemo.model.Employee;
import net.enver.itcompanydemo.model.Position;
import net.enver.itcompanydemo.repository.EmployeeRepository;
import net.enver.itcompanydemo.repository.PositionRepository;
import net.enver.itcompanydemo.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class PositionServiceImpl implements PositionService {

    private final PositionRepository positionRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public PositionServiceImpl(PositionRepository positionRepository, EmployeeRepository employeeRepository) {
        this.positionRepository = positionRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void save(Position position) {
        log.info("In PositionServiceImpl method save.");

        positionRepository.save(position);

        log.info("Position {} successfully saved.", position);
    }

    @Override
    public void update(Long id, Position position) {
        log.info("In PositionServiceImpl method update.");

        Employee findEmployee;
        Set<Employee> employeePositions = new HashSet<>();

        Position updatedPosition = getById(id);
        Set<Employee> employees = position.getEmployees();

        if (updatedPosition != null) {
            if (position.getName() != null) {
                updatedPosition.setName(position.getName());
            }
            if (employees != null) {
                for (Employee employee : employees) {
                    if ((findEmployee = employeeRepository.findById(employee.getId()).orElse(null)) != null) {
                        employeePositions.add(findEmployee);
                    }
                }
                updatedPosition.setEmployees(employeePositions);
            }
            positionRepository.save(updatedPosition);

            log.info("Position with ID {} successfully updated.", updatedPosition);
        }
    }

    @Override
    public Position getById(Long id) {
        log.info("In PositionServiceImpl method getById.");

        Position position = positionRepository.findById(id).orElse(null);
        if (position != null) {
            log.info("Position with ID {} found successfully.", id);
        } else {
            log.info("Position with ID {} not found.", id);
        }
        return position;
    }

    @Override
    public Position findByName(String positionName) {
        log.info("In PositionServiceImpl method findById.");

        Position position = positionRepository.findByName(positionName);
        if (position != null) {
            log.info("Position with name {} found successfully.", positionName);
        } else {
            log.info("Position with name {} not found.", positionName);
        }
        return position;
    }

    @Override
    public List<Position> getAll() {
        log.info("In PositionServiceImpl method getAll.");

        List<Position> positions = positionRepository.findAll();

        log.info("Position list found successfully.");
        return positions;
    }

    @Override
    public void delete(Long id) {
        log.info("In PositionServiceImpl method delete.");

        positionRepository.deleteById(id);

        log.info("Position with ID {} successfully deleted.", id);
    }
}
