package com.nextapp.dto;

import java.io.Serializable;
import java.util.List;

public class MLModelResponse implements Serializable {
    private List<Pest> pestInfo;
    private int recordID;
    private String authStatus;

    public List<Pest> getPestInfo() {
        return pestInfo;
    }
    public void setPestInfo(List<Pest> pestInfo) {
        this.pestInfo = pestInfo;
    }

    public int getRecordID() {
        return recordID;
    }
    public void setRecordID(int recordID) {
        this.recordID = recordID;
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
                ", recordId=" + recordID +
                ", authStatus='" + authStatus + '\'' +
                '}';
    }
}
