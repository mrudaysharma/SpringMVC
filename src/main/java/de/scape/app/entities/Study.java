/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.scape.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.scape.app.mapping.AbstractField2;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author uysharma
 */
@Entity
@Table(name = "Study")
public class Study extends AbstractField2 implements Serializable {

    private String description;
    private short isPlanned;
    private short isInProgress;
    private short isFinished;
    private Date startTime;
    private Date endTime;
    private Long patientId;
    private List<Doctor> doctors;

    public Study() {
    }

    public Study(Long id,String description, short isPlanned, short isInProgress, short isFinished, Date startTime, Date endTime,Long patientId) {
        this.id = id;
        this.description = description;
        this.isPlanned = isPlanned;
        this.isInProgress = isInProgress;
        this.isFinished = isFinished;
        this.startTime = startTime;
        this.endTime = endTime;
        this.patientId = patientId;
    }

    
    
    
    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "study_id", unique = true, nullable = false)
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the description
     */
    @Column(name = "description", length = 50, nullable = true)
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the isPlanned
     */
    @Column(name = "is_planned", length = 1)
    public short getIsPlanned() {
        return isPlanned;
    }

    /**
     * @param isPlanned the isPlanned to set
     */
    public void setIsPlanned(short isPlanned) {
        this.isPlanned = isPlanned;
    }

    /**
     * @return the isInProgress
     */
    @Column(name = "is_inprogress", length = 1)
    public short getIsInProgress() {
        return isInProgress;
    }

    /**
     * @param isInProgress the isInProgress to set
     */
    public void setIsInProgress(short isInProgress) {
        this.isInProgress = isInProgress;
    }

    /**
     * @return the isFinished
     */
    @Column(name = "is_finished", length = 1)
    public short getIsFinished() {
        return isFinished;
    }

    /**
     * @param isFinished the isFinished to set
     */
    public void setIsFinished(short isFinished) {
        this.isFinished = isFinished;
    }

    /**
     * @return the startTime
     */
    @Column(name = "start_time", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the endTime
     */
    @Column(name = "end_time", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * @return the patientId One study belongs to one patientId
     */
    public Long getPatientId() {
        return patientId;
    }

    /**
     * @param patient the patientId to set
     */
    public void setPatientId(Long patient) {
        this.patientId = patient;
    }

    /**
     * @return the doctor
     */
    @Transient
    @XmlTransient
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, targetEntity = Doctor.class)
    @JoinColumn(name = "doctor_id", nullable = true)
    public List<Doctor> getDoctors() {
        return doctors;
    }

    /**
     * @param doctors
     */
    public void setDoctor(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    @Override
    public String toString() {
        return "Study{" +"study_id=" + id  + ", description=" + description + ", isPlanned=" + isPlanned + ", isInProgress=" + isInProgress + ", isFinished=" + isFinished + ", startTime=" + startTime + ", endTime=" + endTime + ", patient=" + patientId + ", doctors=" + doctors + '}';
    }

    
}
