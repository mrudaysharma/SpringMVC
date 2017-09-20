package de.scape.app.service;

import de.scape.app.dao.BaseDao;
import de.scape.app.entities.Study;
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
public class StudyServiceImpl implements StudyService {

    final static Logger logger = Logger.getLogger(StudyServiceImpl.class);

    @Autowired
    BaseDao studyDao;

    @Override
    public Study getStudy(Long id) {
        logger.debug("Getting study with id " + id);
        System.out.println("Study Object " + studyDao.findById(id, Study.class));
        return studyDao.findById(id, Study.class);
    }

    /**
     * Add new study
     *
     * @param study: Study to add
     */
    @Override
    @Transactional(readOnly = false)
    public void addNewStudy(Study study) {
        studyDao.save(study);
        //logger.debug("Id of new Study " + id);
    }

    @Override
    @Transactional(readOnly = false)
    public void update(Study study) {
        studyDao.update(study);
    }

    @Override
    public List<Study> getStudies() {
        logger.debug("Getting list of Study");
        System.out.println("Study Object " + studyDao.findAllEntities(Study.class.getSimpleName()));
        return (List<Study>) studyDao.findAllEntities(Study.class.getSimpleName());
    }

    @Override
    public List<Long> getPlannedPatientIds() {
       return  studyDao.getSession().createQuery("select c.patientId from Study where c.isPlanned=1").getResultList();
    }

    @Override
    public List<Long> getInprogressPatientIds() {
        return  studyDao.getSession().createQuery("select c.patientId from Study where c.isInProgress=1").getResultList();
    }

    @Override
    public List<Long> getFinishedIds() {
       return  studyDao.getSession().createQuery("select c.patientId from Study where c.isFinished=1").getResultList();
    }
    
     @Override
    public Study findByName(String description) {
        logger.debug("Getting Study by name " + description);
        return studyDao.findByName(Study.class.getSimpleName(), description);
    }

}
