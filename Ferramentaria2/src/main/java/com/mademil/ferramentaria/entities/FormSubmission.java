    package com.mademil.ferramentaria.entities;

    import jakarta.persistence.Entity;
    import jakarta.persistence.GeneratedValue;
    import jakarta.persistence.GenerationType;
    import jakarta.persistence.Id;
    import jakarta.persistence.Table;
    import jakarta.persistence.Column;

    import java.time.LocalDateTime;

    @Entity
    @Table(name = "form_submissions")
    public class FormSubmission {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer submissionId;
        private Integer machineId;
        private Integer chuckId;
        private String item;
        private String itemDescription;
        private Integer operationId;
        private String ncName;
        private Integer cycleTime;
        private Integer userId;
        private Integer holdPressure;
        private Integer chuckPressure;
        private Boolean isSaved;

        @Column(name = "created_at", nullable = false, updatable = false)
        private LocalDateTime createdAt;

        public FormSubmission() {}
        
        public Integer getSubmissionId() {
            return submissionId;
        }

        public void setSubmissionId(Integer submissionId) {
            this.submissionId = submissionId;
        }

        public Integer getMachineId() {
            return machineId;
        }

        public void setMachineId(Integer machineId) {
            this.machineId = machineId;
        }

        public Integer getChuckId() {
            return chuckId;
        }

        public void setChuckId(Integer chuckId) {
            this.chuckId = chuckId;
        }

        public String getItem() {
            return item;
        }

        public void setItem(String item) {
            this.item = item;
        }

        public String getItemDescription() {
            return itemDescription;
        }

        public void setItemDescription(String itemDescription) {
            this.itemDescription = itemDescription;
        }

        public Integer getOperationId() {
            return operationId;
        }

        public void setOperationId(Integer operationId) {
            this.operationId = operationId;
        }

        public String getNcName() {
            return ncName;
        }

        public void setNcName(String ncName) {
            this.ncName = ncName;
        }

        public Integer getCycleTime() {
            return cycleTime;
        }

        public void setCycleTime(Integer cycleTime) {
            this.cycleTime = cycleTime;
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public LocalDateTime getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
        }

        public Integer getHoldPressure() {
            return holdPressure;
        }

        public void setHoldPressure(Integer holdPressure) {
            this.holdPressure = holdPressure;
        }

        public Integer getChuckPressure() {
            return chuckPressure;
        }

        public void setChuckPressure(Integer chuckPressure) {
            this.chuckPressure = chuckPressure;
        }

        public Boolean getIsSaved(){
            return this.isSaved;
        }

        public void setIsSaved(Boolean bool){
            this.isSaved = bool;
        }
    }