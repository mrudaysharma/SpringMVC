package de.scape.app.service;

import de.scape.app.entities.Study;
import java.util.List;

public interface StudyService {

    Study getStudy(Long id);

    void addNewStudy(Study study);

    void update(Study study);

    public List<Study> getStudies();

    public List<Long> getPlannedPatientIds();

    public List<Long> getInprogressPatientIds();

    public List<Long> getFinishedIds();
    
    Study findByName(String name);

}
