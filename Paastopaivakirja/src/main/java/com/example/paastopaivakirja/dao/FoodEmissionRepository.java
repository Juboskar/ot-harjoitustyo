package com.example.paastopaivakirja.dao;

import com.example.paastopaivakirja.model.FoodEmission;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Oskari
 */
public interface FoodEmissionRepository extends JpaRepository<FoodEmission, Long> {
    
}
