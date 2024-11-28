package com.mademil.ferramentaria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.mademil.ferramentaria.repositories.FormSubmissionRepository;
import com.mademil.ferramentaria.repositories.MachineRepository;
import com.mademil.ferramentaria.repositories.UserRepository;
import com.mademil.ferramentaria.dto.FormSubmissionDTO;
import com.mademil.ferramentaria.entities.FormSubmission;
import com.mademil.ferramentaria.entities.Machine;
import com.mademil.ferramentaria.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FormSubmissionService {
    @Autowired
    FormSubmissionRepository formSubmissionRepository;

    @Autowired
    MachineRepository machineRepository;

    @Autowired
    UserRepository userRepository;

    public List<FormSubmission> getAllFormSubmissions(){
        return formSubmissionRepository.findAll();
    }

    public Optional<FormSubmission> getFormSubmissionById(Integer id){
        return formSubmissionRepository.findById(id);
    }

    public void saveFormSubmission(FormSubmission formSubmission) throws DataIntegrityViolationException{
        formSubmissionRepository.save(formSubmission);
    }

    public List<FormSubmissionDTO> getAllSubmissionsByMachineIdAndItemAndOperationId(Integer machineId, String item, Integer operationId) {
        List<FormSubmission> formSubmissions = formSubmissionRepository.findAllByMachineIdAndItemAndOperationIdAndIsSavedTrueOrderByCreatedAtDesc(machineId, item, operationId);
        List<FormSubmissionDTO> dtoList = new ArrayList<>();
        
        for (FormSubmission formSubmission : formSubmissions) {
            Machine machine = machineRepository.findById(formSubmission.getMachineId()).orElse(null);
            User user = userRepository.findById(formSubmission.getUserId()).orElse(null);
            String cycleTime = DateAndTimeParser.formatCycleTime(formSubmission.getCycleTime());
            
            if (machine != null) {
                dtoList.add(new FormSubmissionDTO(formSubmission, machine.getMachineName(), cycleTime, user.getFullName()));
            }
        }
        return dtoList;
    }

    public List<FormSubmissionDTO> getLatestSavedSubmissions() {
    List<FormSubmission> formSubmissions = formSubmissionRepository.findLatestSavedSubmissions();
    List<FormSubmissionDTO> dtoList = new ArrayList<>();
    
    for (FormSubmission formSubmission : formSubmissions) {
        Machine machine = machineRepository.findById(formSubmission.getMachineId()).orElse(null);
        User user = userRepository.findById(formSubmission.getUserId()).orElse(null);
        String cycleTime = DateAndTimeParser.formatCycleTime(formSubmission.getCycleTime());
        
        if (machine != null) {
            dtoList.add(new FormSubmissionDTO(formSubmission, machine.getMachineName(), cycleTime, user.getFullName()));
        }
    }
    return dtoList;
}
}
