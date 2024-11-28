package com.mademil.ferramentaria.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "operations")
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer operationId;
    private String operationDescription;

    public Operation() {}

    public Integer getOperationId(){
        return operationId;
    }

    public void setOperationId(Integer operationId){
        this.operationId = operationId;
    }

    public String getOperationDescription(){
        return operationDescription;
    }

    public void setOperationDescription(String operationDescription){
        this.operationDescription = operationDescription;
    }
}
