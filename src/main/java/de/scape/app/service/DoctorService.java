package de.scape.app.service;

import de.scape.app.entities.Doctor;
import java.util.List;

public interface DoctorService {

    Doctor getDoctor(Long id);

    void addNewDoctor(Doctor doctor);

    List<Doctor> getDoctors();

    Doctor findByName(String name);
	
    public boolean isDoctorExist(Doctor doctor);
    
    public List<Doctor> addAllDoctors();
    
    void update(Doctor doctor);

}
