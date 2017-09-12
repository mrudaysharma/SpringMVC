/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.scape.app.controller;

import de.scape.app.entities.Patient;
import de.scape.app.entities.Room;
import de.scape.app.model.PatientModel;
import de.scape.app.service.PatientService;
import de.scape.app.service.RoomService;
import de.scape.app.service.RoomServiceImpl;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author uysharma
 */
@RestController
public class PatientRestController {

    final static Logger logger = Logger.getLogger(PatientRestController.class);

    @Autowired
    PatientService patientService;
    
   
    RoomService roomService;

    @RequestMapping(value = "/patient/all", method = RequestMethod.GET)
    public ResponseEntity<List<Patient>> listAllPatients() {
        List<Patient> patients = patientService.getPatients();
        if (patients.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);//You may decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @RequestMapping(value = "/patient/createOrupdate", method = RequestMethod.POST)
    public ResponseEntity<Void> createPatient(@RequestBody PatientModel patient, UriComponentsBuilder ucBuilder) {
        HttpHeaders headers = new HttpHeaders();
        Patient newPatient = null;
        roomService = new RoomServiceImpl();
        try {
            Date date = null;
            System.out.println("Creating Patient " + patient.getName());
            DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            if(patient.getDob()!=null && !patient.getDob().isEmpty())
            {
            date = format.parse(patient.getDob());
            }
            if (patient.getId() == null) {
                Room room = roomService.findByName(patient.getRoomName());
                newPatient = new Patient(patient.getName(), room.getId());
                newPatient.setGender(patient.getGender());
                if(date!=null)
                {
                    newPatient.setDob(date);
                }
                    
                patientService.addNewPatient(newPatient);
            } else {
                Room room = roomService.findByName(patient.getRoomName());
                newPatient = new Patient(patient.getName(), room.getId());
                newPatient.setGender(patient.getGender());
                  if(date!=null)
                {
                    newPatient.setDob(date);
                }
                patientService.update(newPatient);
            }
            headers.setLocation(ucBuilder.path("/patient/{id}").buildAndExpand(patient.getId()).toUri());
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        } catch (ParseException ex) {
            java.util.logging.Logger.getLogger(PatientRestController.class.getName()).log(Level.SEVERE, null, ex);
             return new ResponseEntity<>(headers, HttpStatus.NOT_MODIFIED);
        }
    }

    @RequestMapping(value = "/patient/all/createOrupdate", method = RequestMethod.POST)
    public ResponseEntity<Void> createOrUpdateListPatients(@RequestBody List<Patient> patients, UriComponentsBuilder ucBuilder) {
        if (patients != null && !patients.isEmpty()) {
            System.out.println("Creating Or Updateing List of Patients " + patients);
            patientService.admitOrUpdatePatientsDetail(patients);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/patient/all/").build().toUri());
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);//You may decide to return HttpStatus.NOT_FOUND
    }
    
    @RequestMapping(value = "/patient/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Patient> getPatient(@PathVariable("id") Long id) {
        System.out.println("Fetching Patient with id " + id);
        Patient patient = patientService.getPatient(id);
        if (patient == null) {
            System.out.println("Patient with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }
}
