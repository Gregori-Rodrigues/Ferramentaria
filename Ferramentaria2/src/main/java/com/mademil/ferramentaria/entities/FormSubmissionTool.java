package com.mademil.ferramentaria.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "form_submissions_tools")
public class FormSubmissionTool {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer submissionId;
    private Integer toolId;
    private Integer toolLength;
    private Integer toolPosition;

    public FormSubmissionTool() {}

    public FormSubmissionTool(Integer submissionId, Integer toolId, Integer toolLength, Integer toolPosition) {
        this.submissionId = submissionId;
        this.toolId = toolId;
        this.toolLength = toolLength;
        this.toolPosition = toolPosition;
    }

    public Integer getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(Integer submissionId) {
        this.submissionId = submissionId;
    }

    public Integer getToolId() {
        return toolId;
    }

    public void setToolId(Integer toolId) {
        this.toolId = toolId;
    }

    public Integer getToolLength() {
        return toolLength;
    }

    public void setToolLength(Integer toolLength) {
        this.toolLength = toolLength;
    }

    public Integer getToolPosition() {
        return toolPosition;
    }

    public void setToolPosition(Integer toolPosition) {
        this.toolPosition = toolPosition;
    }
}
