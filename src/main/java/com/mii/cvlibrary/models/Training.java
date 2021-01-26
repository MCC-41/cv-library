/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibrary.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author habib
 */
@Entity
@Table(name = "training")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Training.findAll", query = "SELECT t FROM Training t")})
public class Training implements Serializable {

    @Basic(optional = false)
    @Lob
    @Column(name = "file")
    private byte[] file;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "institution")
    private String institution;
    @Basic(optional = false)
    @Column(name = "year")
    @Temporal(TemporalType.DATE)
    private Date year;
    @JoinColumn(name = "id_employee", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Employee idEmployee;
    @JoinColumn(name = "id_training_type", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TrainingType idTrainingType;

    public Training() {
    }

    public Training(Integer id) {
        this.id = id;
    }

    public Training(Integer id, String name, String institution, byte[] file, Date year) {
        this.id = id;
        this.name = name;
        this.institution = institution;
        this.file = file;
        this.year = year;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }


    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public Employee getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Employee idEmployee) {
        this.idEmployee = idEmployee;
    }

    public TrainingType getIdTrainingType() {
        return idTrainingType;
    }

    public void setIdTrainingType(TrainingType idTrainingType) {
        this.idTrainingType = idTrainingType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Training)) {
            return false;
        }
        Training other = (Training) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mii.cvlibrary.models.Training[ id=" + id + " ]";
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }
    
}
