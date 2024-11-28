package com.mademil.ferramentaria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mademil.ferramentaria.entities.Operation;

public interface OperationRepository extends JpaRepository<Operation, Integer> {

}
