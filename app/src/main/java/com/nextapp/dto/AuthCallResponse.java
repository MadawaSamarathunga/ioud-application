package com.nextapp.dto;

import java.io.Serializable;

public class AuthCallResponse implements Serializable {
    private int detectionId;
    private String requestedUser;
    private String requestedDate;
    private String detectedPest;
    private String pestImage;
    private String acceptedBy;
    private String status;

    public int getDetectionId() {
        return detectionId;
    }
    public void setDetectionId(int detectionId) {
        this.detectionId = detectionId;
    }

    public String getRequestedUser() {
        return requestedUser;
    }
    public void setRequestedUser(String requestedUser) {
        this.requestedUser = requestedUser;
    }

    public String getRequestedDate() {
        return requestedDate;
    }
    public void setRequestedDate(String requestedDate) {
        this.requestedDate = requestedDate;
    }

    public String getDetectedPest() {
        return detectedPest;
    }
    public void setDetectedPest(String detectedPest) {
        this.detectedPest = detectedPest;
    }

    public String getPestImage() {
        return pestImage;
    }
    public void setPestImage(String pestImage) {
        this.pestImage = pestImage;
    }

    public String getAcceptedBy() {
        return acceptedBy;
    }
    public void setAcceptedBy(String acceptedBy) {
        this.acceptedBy = acceptedBy;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AuthCallResponse{" +
                "detectionId=" + detectionId +
                ", requestedUser='" + requestedUser + '\'' +
                ", requestedDate='" + requestedDate + '\'' +
                ", detectedPest='" + detectedPest + '\'' +
                ", pestImage='" + pestImage + '\'' +
                ", acceptedBy='" + acceptedBy + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
