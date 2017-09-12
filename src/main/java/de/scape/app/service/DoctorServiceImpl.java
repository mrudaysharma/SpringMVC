package de.scape.app.service;

import de.scape.app.dao.BaseDao;
import de.scape.app.entities.Doctor;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author bytesTree
 * @see <a href="http://www.bytestree.com/">BytesTree</a>
 *
 */
@Service("doctorService")
@Transactional(readOnly = true)
public class DoctorServiceImpl implements DoctorService {

    private static final AtomicLong counter = new AtomicLong();

    final static Logger logger = Logger.getLogger(DoctorServiceImpl.class);

    @Autowired
    BaseDao doctorDao;

    @Override
    public Doctor getDoctor(Long id) {
        logger.debug("Getting doctor with id " + id);
        System.out.println("Doctor Object " + doctorDao.findById(id, Doctor.class));
        return doctorDao.findById(id, Doctor.class);
    }

    @Override
    public List<Doctor> getDoctors() {
        logger.debug("Getting list of doctors");
        System.out.println("Doctors Object " + doctorDao.findAllEntities(Doctor.class.getSimpleName()));
        return (List<Doctor>) doctorDao.findAllEntities(Doctor.class.getSimpleName());
    }

    /**
     * Add new doctorList
     *
     * @param doctor: Doctor to add
     */
    @Override
    @Transactional(readOnly = false)
    public void addNewDoctor(Doctor doctor) {
        doctorDao.persist(doctor);
        logger.debug("Id of new Doctor " + doctor);
    }
    
    
    @Override
    @Transactional(readOnly = false)
    public List<Doctor> addAllDoctors() {
        List<Doctor> doctorList = new ArrayList<>();
        doctorList.add(new Doctor(new Long(1), "Sam"));
        doctorList.add(new Doctor(new Long(2), "Tom"));
        doctorList.add(new Doctor(new Long(3), "Jerome"));
        doctorList.add(new Doctor(new Long(4), "Silvia"));
        return doctorList;
    }

    @Override
    public boolean isDoctorExist(Doctor doctor) {
        return doctorDao.findByName(doctor.getClass().getSimpleName(), doctor.getName());
    }

    @Override
    public Doctor findByName(String name) {
        logger.debug("Getting doctor by name " + name);
        List<Doctor> doctorList = new ArrayList<>();
        doctorList = addAllDoctors();
        for(Doctor doctor : doctorList)
        {
            if(doctor.getName().equalsIgnoreCase(name))
            {
                return doctor;
            }
        }
        return new Doctor();
    }

    @Override
    public void update(Doctor doctor) {
       doctorDao.update(doctor);
    }


}
