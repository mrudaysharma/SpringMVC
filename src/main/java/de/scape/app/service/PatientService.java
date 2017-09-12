package de.scape.app.service;

import de.scape.app.entities.Patient;
import java.util.List;

public interface PatientService {

    Patient getPatient(Long id);

    void addNewPatient(Patient patient);

    public List<Patient> getPatients();

    Patient findByName(String name);

    public boolean isPatientExist(Patient patient);
    
    void update(Patient patient);
    
    public void admitOrUpdatePatientsDetail(List<Patient> patients);
}
