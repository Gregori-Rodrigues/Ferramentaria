package com.mademil.ferramentaria.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "machines")
public class Machine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer machineId;
    private Integer machineRecourse;
    private String machineName;

    public Machine() {}

    public Integer getMachineId() {
        return machineId;
    }

    public void setMachineId(Integer id) {
        this.machineId = id;
    }

    public Integer getMachineRecourse() {
        return machineRecourse;
    }

    public void setMachineRecourse(Integer machineRecourse) {
        this.machineRecourse = machineRecourse;
    }

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }
}