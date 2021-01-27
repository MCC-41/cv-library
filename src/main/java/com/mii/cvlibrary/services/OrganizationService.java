/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibrary.services;

import com.mii.cvlibrary.models.Employee;
import com.mii.cvlibrary.models.Organization;
import com.mii.cvlibrary.repositories.OrganizationRepository;
import com.mii.cvlibrary.services.iservices.IService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author habib
 */
@Service
public class OrganizationService implements IService<Organization, Integer>{

    @Autowired
    private OrganizationRepository or;
    @Autowired
    private UserService us;
    
    @Override
    public List<Organization> getAll() {
        return or.findAll();
    }

    @Override
    public Organization getById(Integer id) {
        return or.getOne(id);
    }

    @Override
    public Organization insert(Organization data) {
        return or.save(data);
    }

    @Override
    public Organization update(Integer id, Organization data) {
        Organization organization = getById(id);
        organization.setEmployee(new Employee(us.getId()));
        organization.setName(data.getName());
        organization.setInstitution(data.getInstitution());
        organization.setStartDate(data.getStartDate());
        organization.setEndStart(data.getEndStart());
        return or.save(organization);
    }

    @Override
    public boolean delete(Integer id) {
        try {
            or.delete(getById(id));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
}
