package com.mademil.ferramentaria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mademil.ferramentaria.repositories.OperationRepository;
import com.mademil.ferramentaria.entities.Operation;

import java.util.List;


@Service
public class OperationService {
    @Autowired
    private OperationRepository operationRepository;

    public List<Operation> getAllOperations(){
        return operationRepository.findAll();
    }
}
