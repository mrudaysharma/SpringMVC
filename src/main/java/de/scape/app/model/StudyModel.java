/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.scape.app.model;

/**
 *
 * @author uysharma
 */
public class StudyModel {

    private Long id;
    private String description;
    private short isPlanned;
    private short isInprogress;
    private short isFinished;
    private String startDate;
    private String endDate;
    private Long patientId;

    public StudyModel() {
    }

    
    
    public StudyModel(String description, short isPlanned, short isInprogress, short isFinished, String startDate, Long patientId) {
        
        this.description = description;
        this.isPlanned = isPlanned;
        this.isInprogress = isInprogress;
        this.isFinished = isFinished;
        this.startDate = startDate;
        //this.endDate = endDate;
        this.patientId = patientId;
    }

    
    
    /**
     * @return the id
     */
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
     * @return the isInprogress
     */
    public short getIsInprogress() {
        return isInprogress;
    }

    /**
     * @param isInprogress the isInprogress to set
     */
    public void setIsInprogress(short isInprogress) {
        this.isInprogress = isInprogress;
    }

    /**
     * @return the isFinished
     */
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
     * @return the startDate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the patientId
     */
    public Long getPatientId() {
        return patientId;
    }

    /**
     * @param patientId the patientId to set
     */
    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }
}
