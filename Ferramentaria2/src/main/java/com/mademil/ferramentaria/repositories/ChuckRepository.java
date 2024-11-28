package com.mademil.ferramentaria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mademil.ferramentaria.entities.Chuck;

import java.util.List;

public interface ChuckRepository extends JpaRepository<Chuck, Integer> {

    List<Chuck> findAllByIsDeletedFalse();  
}
