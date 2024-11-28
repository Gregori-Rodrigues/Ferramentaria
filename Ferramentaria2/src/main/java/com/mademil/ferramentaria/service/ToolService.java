package com.mademil.ferramentaria.service;

import com.mademil.ferramentaria.repositories.ToolRepository;
import com.mademil.ferramentaria.entities.Tool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ToolService {
    @Autowired
    private ToolRepository toolRepository;

    public List<Tool> getAllTools(){
        return toolRepository.findAll();
    }

    public List<Tool> getAllActiveTools(){
        return toolRepository.findAllByIsDeletedFalse();
    }

    public Optional<Tool> getToolById(Integer id){
        return toolRepository.findById(id);
    }

    public void saveTool(Tool tool){
        toolRepository.save(tool);
    }
}