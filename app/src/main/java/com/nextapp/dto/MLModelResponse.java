package com.nextapp.dto;

import java.io.Serializable;

public class MLModelResponse implements Serializable {
    private Pest pestInfo;
    private int recordId;
    private String authStatus;

    public Pest getPestInfo() {
        return pestInfo;
    }
    public void setPestInfo(Pest pestInfo) {
        this.pestInfo = pestInfo;
    }

    public int getRecordId() {
        return recordId;
    }
    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public String getAuthStatus() {
        return authStatus;
    }
    public void setAuthStatus(String authStatus) {
        this.authStatus = authStatus;
    }

    @Override
    public String toString() {
        return "MLModelResponse{" +
                "pestInfo=" + pestInfo +
                ", recordId=" + recordId +
                ", authStatus='" + authStatus + '\'' +
                '}';
    }
}
