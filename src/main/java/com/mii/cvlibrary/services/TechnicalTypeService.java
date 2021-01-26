/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibrary.services;

import com.mii.cvlibrary.models.TechnicalType;
import com.mii.cvlibrary.repositories.TechnicalTypeRepository;
import com.mii.cvlibrary.services.iservices.IService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author habib
 */
@Service
public class TechnicalTypeService implements IService<TechnicalType, Integer>{

    @Autowired
    private TechnicalTypeRepository ttr;
    
    @Override
    public List<TechnicalType> getAll() {
        return ttr.findAll();
    }

    @Override
    public TechnicalType getById(Integer id) {
        return ttr.getOne(id);
    }

    @Override
    public TechnicalType insert(TechnicalType data) {
        return ttr.save(data);
    }

    @Override
    public TechnicalType update(Integer id, TechnicalType data) {
        TechnicalType technicalType = getById(id);
        technicalType.setName(data.getName());
        return ttr.save(technicalType);
    }

    @Override
    public boolean delete(Integer id) {
        try {
            ttr.delete(getById(id));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
}
