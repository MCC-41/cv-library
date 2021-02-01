/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibrary.repositories;

import com.mii.cvlibrary.models.Experience;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author habib
 */
@Repository
public interface ExperienceRepository extends JpaRepository<Experience, Integer>{
    @Query(value = "select * from experience where id_employee = :id",nativeQuery = true)
    List<Experience> getByEmployee(@Param("id") Integer id);
}
