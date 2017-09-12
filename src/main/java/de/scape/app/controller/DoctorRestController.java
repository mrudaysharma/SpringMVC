/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.scape.app.controller;

import de.scape.app.entities.Doctor;
import de.scape.app.service.DoctorService;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author uysharma
 */
@RestController
public class DoctorRestController {

    final static Logger logger = Logger.getLogger(DoctorRestController.class);

    @Autowired
    DoctorService doctorService;

    @RequestMapping(value = "/doctor/", method = RequestMethod.GET)
    public ResponseEntity<List<Doctor>> listAllDoctors() {
        List<Doctor> doctors = doctorService.addAllDoctors();

        if (doctors.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }

    @RequestMapping(value = "/doctor/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Doctor> getDoctor(@PathVariable("id") Long id) {
        System.out.println("Fetching Doctor with id " + id);
        Doctor doctor = doctorService.getDoctor(id);
        if (doctor == null) {
            System.out.println("Doctor with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(doctor, HttpStatus.OK);
    }
    
     @RequestMapping(value = "/doctor/{name}", method = RequestMethod.GET)
    public ResponseEntity<Doctor> createDoctor(@PathVariable("name") String doctorName) {
        System.out.println("Creating Doctor " + doctorName);
        Doctor doctor = doctorService.findByName(doctorName);
       if (doctor == null) {
            System.out.println("Doctor with name " + doctorName + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(doctor, HttpStatus.OK);
    }
}
