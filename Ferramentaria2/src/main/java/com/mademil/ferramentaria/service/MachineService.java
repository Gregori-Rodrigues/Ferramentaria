package com.mademil.ferramentaria.service;

import com.mademil.ferramentaria.repositories.MachineRepository;
import com.mademil.ferramentaria.entities.Machine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MachineService {
    @Autowired
    private MachineRepository machineRepository;

    public List<Machine> getAllMachines() {
        return machineRepository.findAll();
    }

    public Optional<Machine> getMachineById(Integer id) {
        return machineRepository.findById(id);
    }
}