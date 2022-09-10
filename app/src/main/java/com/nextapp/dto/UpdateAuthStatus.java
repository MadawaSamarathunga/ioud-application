package com.nextapp.dto;

public class UpdateAuthStatus {
    private String authid;
    private String status;

    public UpdateAuthStatus() {
    }

    public UpdateAuthStatus(String authid, String status) {
        this.authid = authid;
        this.status = status;
    }

    public String getAuthid() {
        return authid;
    }

    public void setAuthid(String authid) {
        this.authid = authid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UpdateAuthStatus{" +
                "authid='" + authid + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
