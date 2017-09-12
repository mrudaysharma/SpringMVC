/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.scape.app.entities;
import de.scape.app.mapping.AbstractField1;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 *
 * @author uysharma
 */
@Entity
@Table(name = "Doctor")
public class Doctor extends AbstractField1 implements Serializable {

    public Doctor() {
    }
    

    public Doctor(String name) {
        this.name = name;
    }
    
     public Doctor(Long id,String name) {
         this.id = id;
        this.name = name;
    }
    

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "doctor_id", unique = true, nullable = false)
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
     * @return the name
     */
    @Column(name = "name", length = 50,nullable = false)
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Doctor{" + "doctor_id="+id+", name="+name+ "}";
    }

    

    

}
