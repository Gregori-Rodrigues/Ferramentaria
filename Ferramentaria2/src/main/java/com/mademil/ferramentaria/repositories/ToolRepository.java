package com.mademil.ferramentaria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mademil.ferramentaria.entities.Tool;

import java.util.List;

public interface ToolRepository extends JpaRepository<Tool, Integer> {

    List<Tool> findAllByIsDeletedFalse();
    
}
