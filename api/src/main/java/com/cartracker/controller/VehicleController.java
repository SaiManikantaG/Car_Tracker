package com.cartracker.controller;

import com.cartracker.entity.Alerts;
import com.cartracker.entity.Readings;
import com.cartracker.entity.Vehicle;
import com.cartracker.service.VehicleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by sai on 6/25/17.
 */
//
@CrossOrigin
@RestController
public class VehicleController {

    @Autowired
    VehicleService service;

/*------------------------------------------- Vehicle services-----------------------------------------------------------*/

// Displaying all vehicles

    @CrossOrigin
    @RequestMapping(value = "/vehicles", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Vehicle> displayAll() {
        return service.displayAll();
    }

// Displaying one vehicles with given Vin
    @CrossOrigin
    @RequestMapping(value = "/vehicles/{vin}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Vehicle displayOne(@PathVariable("vin") String vin) {
        return service.displayOne(vin);
    }


// Creating vehicle data with a POST request
    @CrossOrigin
    @RequestMapping(value = "/vehicles", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void create(@RequestBody Vehicle[] Vehicle) {
        for (Vehicle vh : Vehicle) {
            service.create(vh);
            System.out.println(vh.toString());
        }
    }
// Update the array of vehicle data if already existing with POST request
    @CrossOrigin
    @RequestMapping(value = "/vehicles", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void update(@RequestBody Vehicle[] Vehicle) {
        for (Vehicle vh : Vehicle) {
            service.create(vh);
            System.out.println(vh.toString());
        }
    }

// Update the single vehicle data if already existing with PUT request
    @CrossOrigin
    @RequestMapping(value = "/vehicles/{vin}", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Vehicle update(@PathVariable("vin") String vin, @RequestBody Vehicle vh) {
        return service.update(vin, vh);
    }

// Delete on delete request
    @CrossOrigin
    @RequestMapping(value = "/vehicles/{vin}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("vin") String vin) {
        service.delete(vin);
    }


 /*------------------------------------------- Reading services-----------------------------------------------------------*/


 // Displaying all readings
    @CrossOrigin
    @RequestMapping(value = "/readings", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Readings> displayAllReadings() {
        return service.displayAllReadings();
    }

// Display one reading with given Id
    @CrossOrigin
    @RequestMapping(value = "/readings/{id}", method = RequestMethod.OPTIONS,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Readings displayOneReadings(@PathVariable("id") String id) {
        return service.displayOneReadings(id);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, value = "/readings/{id}/{signal}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Readings> findAll(@PathVariable("id") String vin,@PathVariable("signal") String signal){
        return service.findAll(vin,signal);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, value="/readings/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Object> getLocation(@PathVariable("id") String vin)
    {
        return service.getLocation(vin);
    }

//Create reading for the POST request
    @CrossOrigin
    @RequestMapping(value = "/readings", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Readings createReadings(@RequestBody Readings Readings)
    {
            service.createReadings(Readings);
            return Readings;
    }

//Update reading if already existing reading
    @CrossOrigin
    @RequestMapping(value = "/readings/{id}", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Readings updateReadings(@PathVariable("id") String id, @RequestBody Readings readings) {
        return service.updateReadings(id, readings);
    }

//Delete reading with delete request based on given id
    @CrossOrigin
    @RequestMapping(value = "/readings/{id}", method = RequestMethod.DELETE)
    public void deleteReadings(@PathVariable("id") String id) {
        service.delete(id);
    }


    /// Alert

    @CrossOrigin
    @RequestMapping(value = "/alerts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Alerts> displayAllAlerts() {
        return service.displayAllAlerts();
    }
}
