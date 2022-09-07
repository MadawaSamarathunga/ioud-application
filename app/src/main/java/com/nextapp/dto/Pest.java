package com.nextapp.dto;

import java.io.Serializable;

public class Pest implements Serializable {
    private int pestId;
    private String pestName;
    private String pestScientificName;
    private String pestClassification;
    private String pestDescription;
    private String pestImage;

    public int getPestId() {
        return pestId;
    }
    public void setPestId(int pestId) {
        this.pestId = pestId;
    }

    public String getPestName() {
        return pestName;
    }
    public void setPestName(String pestName) {
        this.pestName = pestName;
    }

    public String getPestScientificName() {
        return pestScientificName;
    }
    public void setPestScientificName(String pestScientificName) {
        this.pestScientificName = pestScientificName;
    }

    public String getPestClassification() {
        return pestClassification;
    }
    public void setPestClassification(String pestClassification) {
        this.pestClassification = pestClassification;
    }

    public String getPestDescription() {
        return pestDescription;
    }
    public void setPestDescription(String pestDescription) {
        this.pestDescription = pestDescription;
    }

    public String getPestImage() {
        return pestImage;
    }
    public void setPestImage(String pestImage) {
        this.pestImage = pestImage;
    }

    @Override
    public String toString() {
        return "Pest{" +
                "pestId=" + pestId +
                ", pestName='" + pestName + '\'' +
                ", pestScientificName='" + pestScientificName + '\'' +
                ", pestClassification='" + pestClassification + '\'' +
                ", pestDescription='" + pestDescription + '\'' +
                ", pestImage='" + pestImage + '\'' +
                '}';
    }
}
