package com.cartracker.service;

import com.cartracker.entity.Alerts;
import com.cartracker.entity.Readings;
import com.cartracker.entity.Vehicle;
import com.cartracker.exception.BadRequestException;
import com.cartracker.exception.ResourceNotFoundException;
import com.cartracker.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by sai on 6/25/17.
 */

@Service
public class VehicleServiceImpl implements VehicleService {


    @Autowired
    VehicleRepository repository;

    /*------------------------------------------- Vehicle services-----------------------------------------------------------*/
    @Transactional
    public List<Vehicle> displayAll() {
        return repository.displayAll();
    }

    @Transactional
    public Vehicle displayOne(String vin) {
        Vehicle existing = repository.displayOne(vin);
        if (existing == null) {
            throw new ResourceNotFoundException("Vehicle with vin " + vin + " doesn't exist.");
        }
        return existing;
    }

    @Transactional
    public Vehicle create(Vehicle vin) {
        Vehicle existing = repository.displayOne(vin.getVin());
        if (existing != null) {
            throw new BadRequestException("Vehicle with Vin " + vin.getVin() + " already exists.");

        }
        return repository.create(vin);
    }

    @Transactional
    public Vehicle update(String vin, Vehicle vh) {
        Vehicle existing = repository.displayOne(vin);
        if (existing == null) {
            throw new ResourceNotFoundException("Vehicle with vin " + vin + " doesn't exist.");
        }
        return repository.update(vh);
    }

    @Transactional
    public void delete(String vin) {
        Vehicle existing = repository.displayOne(vin);
        if (existing == null) {
            throw new ResourceNotFoundException("Vehicle with vin " + vin + " doesn't exist.");
        }
        repository.delete(existing);
    }

    /*------------------------------------------- Reading services-----------------------------------------------------------*/
    @Transactional
    public List<Readings> displayAllReadings() {
        return repository.displayAllReadings();
    }

    @Transactional
    public Readings displayOneReadings(String id) {
        Readings existing = repository.displayOneReadings(id);
        if (existing == null) {
            throw new ResourceNotFoundException("Vehicle Reading with id " + id + " doesn't exist.");
        }
        return existing;
    }

    @Transactional
    public Readings createReadings(Readings readings) {
        if (readings != null) {
            Vehicle existing = repository.displayOne(readings.getId());
            if (existing != null) {
                throw new BadRequestException("Vehicle Reading with id " + readings.getId() + " already exists.");

            }
            //ArrayList<Alerts> alerts = createAlert(readings);
            repository.createReadings(readings);
        } else {
            String ids = UUID.randomUUID().toString();
            Vehicle existing = repository.displayOne(ids);
            if (existing != null) {
                throw new BadRequestException("Vehicle Reading with id " + ids + " already exists.");

            }
            repository.createReadings(readings);

        }
        return readings;
    }

    @Transactional
    public Readings updateReadings(String id, Readings readings) {
        Readings existing = repository.displayOneReadings(id);
        if (existing == null) {
            throw new ResourceNotFoundException("Vehicle Readings with vin " + id + " doesn't exist.");
        }
        return repository.updateReadings(readings);
    }

    @Transactional
    public void deleteReadings(String id) {
        Readings existingReading = repository.displayOneReadings(id);
        if (existingReading == null) {
            throw new ResourceNotFoundException("Vehicle Readings with id " + id + " doesn't exist.");
        }
        repository.deleteReadings(existingReading);
    }

}
