package com.mademil.ferramentaria.controllers;

import com.mademil.ferramentaria.service.FormSubmissionService;
import com.mademil.ferramentaria.dto.FormSubmissionDTO;
import com.mademil.ferramentaria.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ProcessSheetHistoryController {

    @Autowired
    FormSubmissionService formSubmissionService;

    @Autowired
    UserRepository userRepository;
    
    @GetMapping("/ficha/ativas")
    public String serveActiveSheetsTable(Model model){

        List<FormSubmissionDTO> formSubmissions = formSubmissionService.getLatestSavedSubmissions();

        model.addAttribute("formSubmissions", formSubmissions);

        return "active-sheets";
    }

    @GetMapping("/ficha/historico")
    public String serveProcessSheetHistory(
        @RequestParam("machineId") Integer machineId,
        @RequestParam("item") String item,
        @RequestParam("operationId") Integer operationId, 
        Model model){

        List<FormSubmissionDTO> formSubmissions = formSubmissionService.getAllSubmissionsByMachineIdAndItemAndOperationId(machineId, item, operationId);

        model.addAttribute("formSubmissions", formSubmissions);

        return "legacy-sheets";
    }
}
