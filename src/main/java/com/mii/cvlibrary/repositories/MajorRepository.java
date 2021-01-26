/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibrary.repositories;

import com.mii.cvlibrary.models.Major;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author habib
 */
@Repository
public interface MajorRepository extends JpaRepository<Major, Integer>{
    
}
