package de.scape.app.service;

import de.scape.app.dao.BaseDao;
import de.scape.app.entities.Patient;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author uysharma
 */
@Service
@Transactional(readOnly = true)
public class PatientServiceImpl implements PatientService {
    
    final static Logger logger = Logger.getLogger(PatientServiceImpl.class);
    
    @Autowired
    BaseDao patientDao;
    
    @Override
    public Patient getPatient(Long id) {
        logger.debug("Getting patient with id " + id);
        System.out.println("Patient Object " + patientDao.findById(id, Patient.class));
        return patientDao.findById(id, Patient.class);
    }

    /**
     * Add new patient
     *
     * @param patient: Patient to add
     */
    @Override
    @Transactional(readOnly = false)
    public void addNewPatient(Patient patient) {
       patientDao.save(patient);
      //  logger.debug("Id of new Patient " + id);
    }
    
    @Override
    public List<Patient> getPatients() {
        logger.debug("Getting list of Patient");
        System.out.println("Patient Object " + patientDao.findAllEntities(Patient.class.getSimpleName()));
        return (List<Patient>) patientDao.findAllEntities(Patient.class.getSimpleName());
    }
    
    @Override
    public boolean isPatientExist(Patient patient) {
        return patientDao.findByName(patient.getClass().getSimpleName(), patient.getName());
    }
    
    @Override
    public Patient findByName(String name) {
        logger.debug("Getting Patient by name " + name);
        System.out.println("Patient Object " + patientDao.findByName(Patient.class.getSimpleName(), name));
        return patientDao.findByName(Patient.class.getSimpleName(), name);
    }
    
    @Override
    public void update(Patient patient) {
        patientDao.update(patient);
    }
    
    @Override
    public void admitOrUpdatePatientsDetail(List<Patient> patients) {
        if (patients != null && !patients.isEmpty()) {
            patients.forEach((Patient patient) -> {
                if (patient.getId() != null) {
                    addNewPatient(patient);
                } else {
                    update(patient);
                }
            });
        }
    }
}
