package com.cartracker.repository;

import com.cartracker.entity.Alerts;
import com.cartracker.entity.Readings;
import com.cartracker.entity.Tires;
import com.cartracker.entity.Vehicle;
import com.sun.org.apache.bcel.internal.generic.ALOAD;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sai on 6/25/17.
 */

@Repository
public class VehicleRepositoryImpl implements VehicleRepository {

    @PersistenceContext
    private EntityManager entityManager;


    public List<Vehicle> displayAll() {

        TypedQuery<Vehicle> query = entityManager.createNamedQuery("Vehicle.displayAll", Vehicle.class);
        return query.getResultList();

    }

    public Vehicle displayOne(String vin) {
        return entityManager.find(Vehicle.class, vin);

    }


    public Vehicle create(Vehicle vh) {
        entityManager.persist(vh);
        return vh;

    }

    public Vehicle update(Vehicle vh) {
        return entityManager.merge(vh);

    }

    public void delete(Vehicle vh) {
        entityManager.remove(vh);

    }

    public List<Readings> displayAllReadings() {

        TypedQuery<Readings> query = entityManager.createNamedQuery("Readings.displayAllReading", Readings.class);
        return query.getResultList();
    }

    public Readings displayOneReadings(String id) {
        return entityManager.find(Readings.class, id);
    }

    public Readings createReadings(Readings readings) {
       /* entityManager.persist(readings);
        return readings;*/

        Tires tires = readings.getTires();

        float engineRpm = readings.getEngineRpm();
        Vehicle vehicle = entityManager.find(Vehicle.class,readings.getVin());
        float redlineRpm = vehicle.getRedlineRpm();

        if(engineRpm > redlineRpm)
        {
            Alerts alert = new Alerts();
            alert.setPriority("High");
            alert.setType("RPM exceeded");
            alert.setVin(readings.getVin());
            alert.setTimestamp(readings.getTimestamp());

            entityManager.persist(alert);
        }

        double fuelLimit = (0.1)*(vehicle.getMaxFuelVolume());
        if(readings.getFuelVolume()< fuelLimit)
        {
            Alerts alert = new Alerts();
            alert.setPriority("Medium");
            alert.setType("Low Fuel");
            alert.setVin(readings.getVin());
            alert.setTimestamp(readings.getTimestamp());

            entityManager.persist(alert);
        }

        float totalPressure = tires.getFrontLeft() + tires.getFrontRight() + tires.getRearLeft() + tires.getRearRight();
        if(totalPressure < 128 || totalPressure > 144 )
        {
            Alerts alert = new Alerts();
            alert.setPriority("Low");
            alert.setType("Tire Pressure");
            alert.setVin(readings.getVin());
            alert.setTimestamp(readings.getTimestamp());

            entityManager.persist(alert);
        }

        if(readings.isEngineCoolantLow() || readings.isCheckEngineLightOn())
        {
            Alerts alert = new Alerts();
            alert.setPriority("Low");
            alert.setType("Engine Coolant or Engine Light");
            alert.setVin(readings.getVin());
            alert.setTimestamp(readings.getTimestamp());

            entityManager.persist(alert);
        }

        entityManager.persist(readings);
        return readings;

    }

    public Readings updateReadings(Readings readings) {
        return entityManager.merge(readings);

    }

    public void deleteReadings(Readings readings) {
        entityManager.remove(readings);

    }

}
