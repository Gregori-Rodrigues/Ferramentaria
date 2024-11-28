package com.mademil.ferramentaria.service;

import com.mademil.ferramentaria.dto.FormSubmissionToolDTO;
import com.mademil.ferramentaria.entities.Tool;
import com.mademil.ferramentaria.repositories.FormSubmissionToolRepository;
import com.mademil.ferramentaria.repositories.ToolRepository;
import com.mademil.ferramentaria.entities.FormSubmissionTool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FormSubmissionToolService {
    @Autowired
    private FormSubmissionToolRepository formSubmissionToolRepository;

    @Autowired
    private ToolRepository toolRepository;

    public List<FormSubmissionTool> getAllToolsBySubmissionId(Integer submissionId){
        return formSubmissionToolRepository.findAllBySubmissionId(submissionId);
    }

    public List<FormSubmissionToolDTO> getAllToolsDTOBySubmissionId(Integer submissionId) {
        List<FormSubmissionTool> formSubmissionTools = formSubmissionToolRepository.findAllBySubmissionId(submissionId);
        List<FormSubmissionToolDTO> dtoList = new ArrayList<>();

        for (FormSubmissionTool formSubmissionTool : formSubmissionTools){
            Tool tool = toolRepository.findById(formSubmissionTool.getToolId()).orElse(null);

            if (tool != null) {
                dtoList.add(new FormSubmissionToolDTO(tool.getToolName(), formSubmissionTool));
            }
        }
        return dtoList;
    }

    public void saveAllSubmissionToolsFromList(List<FormSubmissionTool> formSubmissionToolList){
        formSubmissionToolRepository.saveAll(formSubmissionToolList);
    }

    public void saveFormSubmissionTool(FormSubmissionTool formSubmissionTool){
        formSubmissionToolRepository.save(formSubmissionTool);
    }
}