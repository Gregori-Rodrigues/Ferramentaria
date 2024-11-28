package com.mademil.ferramentaria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mademil.ferramentaria.entities.Machine;

public interface MachineRepository extends JpaRepository<Machine, Integer> {
    
}
