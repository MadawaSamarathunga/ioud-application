package com.nextapp.dto;

import android.view.View;

import java.io.Serializable;

public class AuthCallResponse implements Serializable {
    private int detectionId;
    private String requestBy;
    private String requestDate;
    private String detectedPest;
    private String pestImage;
    private String requestUserNumber;
    private String status;
    private String temperature;
    private View.OnClickListener btnClickListener;

    public int getDetectionId() {
        return detectionId;
    }
    public void setDetectionId(int detectionId) {
        this.detectionId = detectionId;
    }

    public String getRequestBy() {
        return requestBy;
    }
    public void setRequestBy(String requestBy) {
        this.requestBy = requestBy;
    }

    public String getRequestDate() {
        return requestDate;
    }
    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
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

    public String getRequestUserNumber() {
        return requestUserNumber;
    }
    public void setRequestUserNumber(String requestUserNumber) {
        this.requestUserNumber = requestUserNumber;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getTemperature() {
        return temperature;
    }
    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public View.OnClickListener getBtnClickListener() {
        return btnClickListener;
    }
    public void setBtnClickListener(View.OnClickListener btnClickListener) {
        this.btnClickListener = btnClickListener;
    }

    @Override
    public String toString() {
        return "AuthCallResponse{" +
                "detectionId=" + detectionId +
                ", requestedUser='" + requestBy + '\'' +
                ", requestedDate='" + requestDate + '\'' +
                ", detectedPest='" + detectedPest + '\'' +
                ", pestImage='" + pestImage + '\'' +
                ", acceptedBy='" + requestUserNumber + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
