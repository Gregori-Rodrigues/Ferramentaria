package com.mademil.ferramentaria.controllers;

import com.mademil.ferramentaria.service.*;
import com.mademil.ferramentaria.dto.FormSubmissionToolDTO;
import com.mademil.ferramentaria.entities.*;
import com.mademil.ferramentaria.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.List;
import java.util.Arrays;

@Controller
public class ProcessSheetController {
    
    @Autowired
    ChuckService chuckService;

    @Autowired
    MachineService machineService;

    @Autowired
    FormSubmissionService formSubmissionService;

    @Autowired
    FormSubmissionToolService formSubmissionToolService;

    @Autowired
    OperationService operationService;

    @Autowired
    ToolService toolService;

    @Autowired
    HtmlDocumentService htmlDocumentService;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/formulario-ficha")
    public String processSheetForm(
    @AuthenticationPrincipal UserDetails springUser,
    @RequestParam(value = "error", required = false) String errorMessage,
    @RequestParam(value = "submissionId", required = false) Integer submissionId,
    Model model){

        User user = userRepository.findByUsername(springUser.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));
        List<Machine> machines = machineService.getAllMachines();
        List<Operation> operations = operationService.getAllOperations();
        List<Chuck> chucks = chuckService.getAllActiveChucks();
        List<Tool> tools = toolService.getAllActiveTools();

        model.addAttribute("user", user);
        model.addAttribute("machines", machines);
        model.addAttribute("operations", operations);
        model.addAttribute("chucks", chucks);
        model.addAttribute("tools", tools);

        if (errorMessage != null) {
            model.addAttribute("error", errorMessage);
        }

        //if the user provides a valid submissionId, the form is prepopulated with the data from that submission.
        if (submissionId != null) {
            Optional<FormSubmission> optionalFormSubmission = formSubmissionService.getFormSubmissionById(submissionId);

            if (optionalFormSubmission.isPresent()) {
                FormSubmission formSubmission = optionalFormSubmission.get();
                List<FormSubmissionTool> toolList = formSubmissionToolService.getAllToolsBySubmissionId(submissionId);
                String cycleTime = DateAndTimeParser.formatCycleTime(formSubmission.getCycleTime());

                model.addAttribute("formSubmission", formSubmission);
                model.addAttribute("toolList", toolList);
                model.addAttribute("cycleTime", cycleTime);
            }else{
                model.addAttribute("error", "Não existe formulário com o ID: " + submissionId);
            }
        }
        return "form-sheet";
    }
    
    @PostMapping("/salvar-formulario")
    public String saveFormSubmission(
        @RequestParam("machineId") Integer machineId,
        @RequestParam("chuckId") Integer chuckId,
        @RequestParam("item") String item,
        @RequestParam("itemDescription") String itemDescription,
        @RequestParam("operationId") Integer operationId,
        @RequestParam("ncName") String ncName,
        @RequestParam("cycleTime") String cycleTime,
        @RequestParam("userId") Integer userId,
        @RequestParam(value = "holdPressure", required = false) Integer holdPressure,
        @RequestParam("chuckPressure") Integer chuckPressure,

        @RequestParam("tool1Id") Integer tool1Id,
        @RequestParam("tool1Position") Integer tool1Position,
        @RequestParam(value = "tool1Length", required = false) Integer tool1Length,
        
        @RequestParam(value = "tool2Id", required = false) Integer tool2Id,
        @RequestParam(value = "tool2Position", required = false) Integer tool2Position,
        @RequestParam(value = "tool2Length", required = false) Integer tool2Length,
    
        @RequestParam(value = "tool3Id", required = false) Integer tool3Id,
        @RequestParam(value = "tool3Position", required = false) Integer tool3Position,
        @RequestParam(value = "tool3Length", required = false) Integer tool3Length,
    
        @RequestParam(value = "tool4Id", required = false) Integer tool4Id,
        @RequestParam(value = "tool4Position", required = false) Integer tool4Position,
        @RequestParam(value = "tool4Length", required = false) Integer tool4Length,
    
        @RequestParam(value = "tool5Id", required = false) Integer tool5Id,
        @RequestParam(value = "tool5Position", required = false) Integer tool5Position,
        @RequestParam(value = "tool5Length", required = false) Integer tool5Length,
    
        @RequestParam(value = "tool6Id", required = false) Integer tool6Id,
        @RequestParam(value = "tool6Position", required = false) Integer tool6Position,
        @RequestParam(value = "tool6Length", required = false) Integer tool6Length,
    
        @RequestParam(value = "tool7Id", required = false) Integer tool7Id,
        @RequestParam(value = "tool7Position", required = false) Integer tool7Position,
        @RequestParam(value = "tool7Length", required = false) Integer tool7Length,
    
        @RequestParam(value = "tool8Id", required = false) Integer tool8Id,
        @RequestParam(value = "tool8Position", required = false) Integer tool8Position,
        @RequestParam(value = "tool8Length", required = false) Integer tool8Length,
    
        @RequestParam(value = "tool9Id", required = false) Integer tool9Id,
        @RequestParam(value = "tool9Position", required = false) Integer tool9Position,
        @RequestParam(value = "tool9Length", required = false) Integer tool9Length,
    
        @RequestParam(value = "tool10Id", required = false) Integer tool10Id,
        @RequestParam(value = "tool10Position", required = false) Integer tool10Position,
        @RequestParam(value = "tool10Length", required = false) Integer tool10Length,
    
        RedirectAttributes redirectAttributes
    ) {
        try {
            FormSubmission formSubmission = new FormSubmission();

            formSubmission.setMachineId(machineId);
            formSubmission.setChuckId(chuckId);
            formSubmission.setItem(item.toUpperCase());
            formSubmission.setItemDescription(itemDescription.toUpperCase());
            formSubmission.setOperationId(operationId); 
            formSubmission.setNcName(ncName.toUpperCase());
            formSubmission.setCycleTime(DateAndTimeParser.parseCycleTimeToTotalSeconds(cycleTime));
            formSubmission.setUserId(userId);
            formSubmission.setChuckPressure(chuckPressure);
            formSubmission.setHoldPressure(holdPressure);
            formSubmission.setCreatedAt(LocalDateTime.now());
            formSubmission.setIsSaved(false);;
            formSubmissionService.saveFormSubmission(formSubmission);

            Integer submissionId = formSubmission.getSubmissionId(); 

            List<Integer> toolIds = Arrays.asList(
                tool1Id, tool2Id, tool3Id, tool4Id, tool5Id,
                tool6Id, tool7Id, tool8Id, tool9Id, tool10Id);

            List<Integer> toolPositions = Arrays.asList(
                tool1Position, tool2Position, tool3Position, tool4Position, tool5Position,
                tool6Position,tool7Position, tool8Position, tool9Position, tool10Position);

            List<Integer> toolLengths = Arrays.asList(
                tool1Length, tool2Length, tool3Length, tool4Length, tool5Length,
                tool6Length, tool7Length, tool8Length, tool9Length, tool10Length);

            List<FormSubmissionTool> formSubmissionToolList = new ArrayList<>();
            for (int i = 0; i < toolIds.size(); i++) {
                Integer toolId = toolIds.get(i);
                if (toolId != null) {
                    formSubmissionToolList.add(new FormSubmissionTool(submissionId, toolId, toolLengths.get(i), toolPositions.get(i)));
                }
            }
            formSubmissionToolService.saveAllSubmissionToolsFromList(formSubmissionToolList);

            /*after everything from the form-sheet is persisted, redirect to another page that will take the form data from the db to avoid
            having a html-document without a related form-submission*/
            redirectAttributes.addAttribute("submissionId", submissionId);
        }catch (DataIntegrityViolationException e) {
            System.err.println(e.getMessage());
            redirectAttributes.addAttribute("error", "Erro de integridade de dados: entre em contato com o administrador");
            return "redirect:/formulario-ficha";
        }catch (Exception e){
            System.err.println(e.getMessage());
            redirectAttributes.addAttribute("error", "Erro inesperado: entre em contato com o administrador");
            return "redirect:/formulario-ficha";
        }
        return "redirect:/ficha";
    }

    @GetMapping("/ficha")
    public String serveProcessSheet(
        @RequestParam("submissionId") Integer submissionId,
        Model model,
        RedirectAttributes redirectAttributes,
        @AuthenticationPrincipal UserDetails springUser) {

        if (submissionId != null) {
           Optional<FormSubmission> optionalFormSubmission = formSubmissionService.getFormSubmissionById(submissionId);

           if (optionalFormSubmission.isPresent()) {
                FormSubmission formSubmission = optionalFormSubmission.get();
                List<FormSubmissionToolDTO> formSubmissionTools = formSubmissionToolService.getAllToolsDTOBySubmissionId(submissionId);
                Machine machine = machineService.getMachineById(formSubmission.getMachineId()).orElse(null);
                Chuck chuck = chuckService.getChuckById(formSubmission.getChuckId()).orElse(null);
                String cycleTime = DateAndTimeParser.formatCycleTime(formSubmission.getCycleTime());
                Integer piecesPerHour = 3600 / formSubmission.getCycleTime(); //cycle time is in seconds in the db
                String dateString = DateAndTimeParser.parseTimesTampIntoCustomFormat(formSubmission.getCreatedAt());// dd/mm/yy - hh:mm:ss
                User user = userRepository.findByUsername(springUser.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));

                model.addAttribute("formSubmission", formSubmission);
                model.addAttribute("formSubmissionTools", formSubmissionTools);
                model.addAttribute("machine", machine);
                model.addAttribute("chuck", chuck);
                model.addAttribute("piecesPerHour", piecesPerHour);
                model.addAttribute("cycleTime", cycleTime);
                model.addAttribute("dateString", dateString);
                model.addAttribute("user", user);

                return "process-sheet";
           }else{
                redirectAttributes.addAttribute("error", "Documento não encontrado: nenhum formulário com ID: " + submissionId);
                return "redirect:/formulario-ficha";
           }
        }
        redirectAttributes.addAttribute("error", "Erro inesperado: entre em contato com o administrador");
        return "redirect:/formulario-ficha";
    }

 @GetMapping("/ficha/documento")
    public ResponseEntity<String> serveSavedProcessSheet(@RequestParam("submissionId") Integer submissionId) {

        HtmlDocument htmlDocument = htmlDocumentService.getHtmlDocumentBySubmissionId(submissionId);

        if (htmlDocument == null) {
            return new ResponseEntity<>("Documento não encontrado: nenhum documento com ID: " + submissionId, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(htmlDocument.getHtmlData(), HttpStatus.OK);
    }

    @PostMapping("/salvar-ficha")
    public String saveProcessSheetToDatabase(
        @RequestParam("submissionId") Integer submissionId,
        @RequestParam("htmlData") String htmlData,
        RedirectAttributes redirectAttributes,
        Model model){

        Optional<FormSubmission> optionalFormSubmission = formSubmissionService.getFormSubmissionById(submissionId);

        if (optionalFormSubmission.isPresent() && htmlData != null) {
            HtmlDocument htmlDocument = new HtmlDocument();
            htmlDocument.setSubmissionId(submissionId);
            htmlDocument.setHtmlData(htmlData);
            htmlDocumentService.saveHtmlDocument(htmlDocument);

            FormSubmission formSubmission = optionalFormSubmission.get();
            formSubmission.setIsSaved(true);
            formSubmissionService.saveFormSubmission(formSubmission);
        }else{
            redirectAttributes.addAttribute("error", "Erro ao salvar ficha de processo: parametros críticos não encontrados, entre em contato com o administrador");
        }
        return "redirect:/formulario-ficha";
    }
}
