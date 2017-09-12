/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.scape.app.entities;
import de.scape.app.mapping.AbstractField1;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author uysharma
 */
@Entity
@Table(name = "Patient")
public class Patient extends AbstractField1 implements Serializable{
    
    private static final long serialVersionUID = 4910225916550731446L;

    private String gender;
    private Date dob;
    private Long roomId;

    public Patient() {
    }
    
     public Patient(Long id, String name, Long roomId) {
        this.name = name;
        this.id = id;
        this.roomId = roomId;
    }
     
     public Patient(String name, Long roomId) {
        this.name = name;
        this.roomId = roomId;
    }
    
    public Patient(String name, String gender, Date dob) {
        this.name = name;
        this.gender = gender;
        this.dob = dob;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "patient_id", unique = true, nullable = false)
    @Override
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    @Override
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    @Column(name = "name", length = 50, nullable = false)
    @Override
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the gender
     */
    @Column(name = "gender", length = 1, nullable = true)
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the dob
     */
    @Column(name = "dob", length = 10, nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getDob() {
        return dob;
    }

    /**
     * @param dob the dob to set
     */
    public void setDob(Date dob) {
        this.dob = dob;
    }
      
    
     /**
     * @return the patientsInRoom
     * One Room has many Patients OneToMany Relationship
     */
    public Long getRoomId() {
        return roomId;
    }

    /**
     * @param roomId
     */
    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    @Override
    public String toString() {
        return "Patient{" +"patient_id=" + id + ", name="+ name + ", gender=" + gender + ", dob=" + dob + ", patientInRoom=" + roomId + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.gender);
        hash = 79 * hash + Objects.hashCode(this.dob);
        hash = 79 * hash + Objects.hashCode(this.roomId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Patient other = (Patient) obj;
        if (!Objects.equals(this.gender, other.gender)) {
            return false;
        }
        if (!Objects.equals(this.dob, other.dob)) {
            return false;
        }
        if (!Objects.equals(this.roomId, other.roomId)) {
            return false;
        }
        return true;
    }
  
  


}
