/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibrary.repositories;

import com.mii.cvlibrary.models.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author habib
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    Optional<User> getByUsername(String name);
    
    @Query(value="SELECT count(*) FROM user",nativeQuery = true)
    int totalUser();
}
