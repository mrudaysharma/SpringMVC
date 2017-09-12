/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.scape.app.controller;

import de.scape.app.entities.Study;
import de.scape.app.model.StudyModel;
import de.scape.app.service.StudyService;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
public class StudyRestController {

    final static Logger logger = Logger.getLogger(StudyRestController.class);

    @Autowired
    StudyService studyService;

    @RequestMapping(value = "/study/", method = RequestMethod.GET)
    public ResponseEntity<List<Study>> listAllStudys() {
        List<Study> studies = studyService.getStudies();

        if (studies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<>(studies, HttpStatus.OK);
    }

    @RequestMapping(value = "/study/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Study> getStudy(@PathVariable("id") long id) {
        System.out.println("Fetching Study with id " + id);
        Study study = studyService.getStudy(id);
        if (study == null) {
            System.out.println("Study with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(study, HttpStatus.OK);
    }

    @RequestMapping(value = "/study/create", method = RequestMethod.POST)
    public ResponseEntity<Void> createStudy(@RequestBody StudyModel study, UriComponentsBuilder ucBuilder) {
        HttpHeaders headers = new HttpHeaders();
        try {
            Study storeStudy = null;
            System.out.println("Creating Study " + study.getDescription());
            DateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

            if (study.getId() == null) {
                storeStudy = new Study();
            }

            storeStudy.setDescription(study.getDescription());
            storeStudy.setIsFinished(study.getIsFinished());
            storeStudy.setIsPlanned(study.getIsPlanned());
            storeStudy.setIsInProgress(study.getIsInprogress());

            if (study.getStartDate() != null && !study.getStartDate().isEmpty()) {
                storeStudy.setStartTime(format.parse(study.getStartDate()));
            }

            if (study.getEndDate() != null && !study.getEndDate().isEmpty()) {
                storeStudy.setEndTime(format.parse(study.getEndDate()));
            }

            studyService.addNewStudy(storeStudy);

            headers.setLocation(ucBuilder.path("/study/").build().toUri());
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        } catch (ParseException ex) {
            java.util.logging.Logger.getLogger(StudyRestController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/study/isPlanned", method = RequestMethod.POST)
    public ResponseEntity<List<Long>> getIsPlannedStudy() {
        HttpHeaders headers = new HttpHeaders();
        List<Long> studyStudys = null;
        try {
            System.out.println("Planned patient study");
            studyStudys = studyService.getPlannedPatientIds();
            if (studyStudys == null || studyStudys.isEmpty()) {
                System.out.println("Study study with is not planned ");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(StudyRestController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(studyStudys, HttpStatus.OK);
    }

    @RequestMapping(value = "/study/isInProgress", method = RequestMethod.POST)
    public ResponseEntity<List<Long>> getIsInProgressStudy() {
        HttpHeaders headers = new HttpHeaders();
        List<Long> studyStudys = null;
        try {
            System.out.println("In Progress patient study");
            studyStudys = studyService.getInprogressPatientIds();
            if (studyStudys == null || studyStudys.isEmpty()) {
                System.out.println("Study study with is not in progress ");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(StudyRestController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(studyStudys, HttpStatus.OK);
    }

    @RequestMapping(value = "/study/isFinished", method = RequestMethod.POST)
    public ResponseEntity<List<Long>> getIsFinishedStudy() {
        HttpHeaders headers = new HttpHeaders();
        List<Long> studyStudys = null;
        try {
            System.out.println("Finished patient study");
            studyStudys = studyService.getFinishedIds();
            if (studyStudys == null || studyStudys.isEmpty()) {
                System.out.println("Study study with is not finished ");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(StudyRestController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(studyStudys, HttpStatus.OK);
    }
}
