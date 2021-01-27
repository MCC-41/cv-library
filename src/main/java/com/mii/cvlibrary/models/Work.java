/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibrary.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import lombok.Data;

/**
 *
 * @author habib
 */
@Entity
@Table(name = "work")
@XmlRootElement
@Data
@NamedQueries({
    @NamedQuery(name = "Work.findAll", query = "SELECT w FROM Work w")})
public class Work implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "company_name")
    private String companyName;
    @Basic(optional = false)
    @Column(name = "position")
    private String position;
    @Basic(optional = false)
    @Lob
    @Column(name = "job_desc")
    private String jobDesc;
    @Basic(optional = false)
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Basic(optional = false)
    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @JoinColumn(name = "id_employee", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Employee employee;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "work", fetch = FetchType.LAZY)
    private List<WorkProjectSpec> workProjectSpecList;

    @XmlTransient
    public List<WorkProjectSpec> getWorkProjectSpecList() {
        return workProjectSpecList;
    }

    public void setWorkProjectSpecList(List<WorkProjectSpec> workProjectSpecList) {
        this.workProjectSpecList = workProjectSpecList;
    }
    
}
