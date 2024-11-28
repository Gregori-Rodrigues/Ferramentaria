package com.mademil.ferramentaria.service;

import com.mademil.ferramentaria.repositories.ChuckRepository;
import com.mademil.ferramentaria.entities.Chuck;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public class ChuckService {
    @Autowired
    private ChuckRepository chuckRepository;

    public List<Chuck> getAllChucks(){
        return chuckRepository.findAll();
    }

    public List<Chuck> getAllActiveChucks(){
        return chuckRepository.findAllByIsDeletedFalse();
    }

    public Optional<Chuck> getChuckById(Integer id){
        return chuckRepository.findById(id);
    }

    public void saveChuck(Chuck chuck){
        chuckRepository.save(chuck);
    }

    public void deleteChuck(Chuck chuck){
        chuckRepository.delete(chuck);
    }
}