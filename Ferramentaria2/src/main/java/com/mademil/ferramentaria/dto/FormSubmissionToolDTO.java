package com.mademil.ferramentaria.dto;

import com.mademil.ferramentaria.entities.FormSubmissionTool;

public class FormSubmissionToolDTO {

    private Integer submissionId;
    private Integer toolId;
    private String toolName;
    private Integer toolLength;
    private Integer toolPosition;

    public FormSubmissionToolDTO(String toolName, FormSubmissionTool formSubmissionTool) {
        this.submissionId = formSubmissionTool.getSubmissionId();
        this.toolId = formSubmissionTool.getToolId();
        this.toolName = toolName;
        this.toolLength = formSubmissionTool.getToolLength();
        this.toolPosition = formSubmissionTool.getToolPosition();
    }

    public Integer getSubmissionId() {
        return submissionId;
    }

    public Integer getToolId(){
        return this.toolId;
    }

    public String getToolName() {
        return toolName;
    }

    public Integer getToolLength() {
        return toolLength;
    }

    public Integer getToolPosition() {
        return toolPosition;
    }
}
