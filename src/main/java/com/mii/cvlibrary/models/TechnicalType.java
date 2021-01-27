/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibrary.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import lombok.Data;

/**
 *
 * @author habib
 */
@Entity
@Table(name = "technical_type")
@XmlRootElement
@Data
@NamedQueries({
    @NamedQuery(name = "TechnicalType.findAll", query = "SELECT t FROM TechnicalType t")})
public class TechnicalType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "technicalType", fetch = FetchType.LAZY)
    private List<Technical> technicalList;

    @XmlTransient
    public List<Technical> getTechnicalList() {
        return technicalList;
    }

    public void setTechnicalList(List<Technical> technicalList) {
        this.technicalList = technicalList;
    }
    
}
