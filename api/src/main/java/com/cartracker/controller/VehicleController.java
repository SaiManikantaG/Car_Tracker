package com.cartracker.controller;

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

@CrossOrigin(origins = "http://mocker.egen.io", maxAge = 3600)
@RestController
public class VehicleController {

    @Autowired
    VehicleService service;

/*------------------------------------------- Vehicle services-----------------------------------------------------------*/

// Displaying all vehicles
    @RequestMapping(value = "/vehicles", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Vehicle> displayAll() {
        return service.displayAll();
    }

// Displaying one vehicles with given Vin
    @RequestMapping(value = "/vehicles/{vin}", method = RequestMethod.OPTIONS,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Vehicle displayOne(@PathVariable("vin") String vin) {
        return service.displayOne(vin);
    }


// Creating vehicle data with a POST request
    @RequestMapping(value = "/vehicles", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void create(@RequestBody Vehicle[] Vehicle) {
        for (Vehicle vh : Vehicle) {
            service.create(vh);
            System.out.println(vh.toString());
        }
    }
// Update the array of vehicle data if already existing with POST request
    @RequestMapping(value = "/vehicles", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void update(@RequestBody Vehicle[] Vehicle) {
        for (Vehicle vh : Vehicle) {
            service.create(vh);
            System.out.println(vh.toString());
        }
    }

// Update the single vehicle data if already existing with PUT request
    @RequestMapping(value = "/vehicles/{vin}", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Vehicle update(@PathVariable("vin") String vin, @RequestBody Vehicle vh) {
        return service.update(vin, vh);
    }

// Delete on delete request
    @RequestMapping(value = "/vehicles/{vin}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("vin") String vin) {
        service.delete(vin);
    }


 /*------------------------------------------- Reading services-----------------------------------------------------------*/


 // Displaying all readings
    @RequestMapping(value = "/readings", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Readings> displayAllReadings() {
        return service.displayAllReadings();
    }

// Display one reading with given Id
    @RequestMapping(value = "/readings/{id}", method = RequestMethod.OPTIONS,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Readings displayOneReadings(@PathVariable("id") String id) {
        return service.displayOneReadings(id);
    }

//Create reading for the POST request
    @RequestMapping(value = "/readings", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void createReadings(@RequestBody Readings Readings){
            service.createReadings(Readings);
    }

//Update reading if already existing reading
    @RequestMapping(value = "/readings/{id}", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Readings updateReadings(@PathVariable("id") String id, @RequestBody Readings readings) {
        return service.updateReadings(id, readings);
    }

//Delete reading with delete request based on given id
    @RequestMapping(value = "/readings/{id}", method = RequestMethod.DELETE)
    public void deleteReadings(@PathVariable("id") String id) {
        service.delete(id);
    }
}
