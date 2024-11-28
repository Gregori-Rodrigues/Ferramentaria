package com.mademil.ferramentaria.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "chucks")
public class Chuck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer chuckId;
    private String chuckName;
    @Lob
    private byte[] chuckImage;
    private String chuckLocation;
    private Boolean isDeleted;

    public Chuck() {}

    public Integer getChuckId() {
        return chuckId;
    }

    public void setChuckId(Integer chuckId) {
        this.chuckId = chuckId;
    }

    public String getChuckName() {
        return chuckName;
    }

    public void setChuckName(String chuckName) {
        this.chuckName = chuckName;
    }

    public byte[] getChuckImage() {
        return chuckImage;
    }

    public void setChuckImage(byte[] chuckImage) {
        this.chuckImage = chuckImage;
    }

    public String getChuckLocation() {
        return chuckLocation;
    }

    public void setChuckLocation(String chuckLocation) {
        this.chuckLocation = chuckLocation;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}