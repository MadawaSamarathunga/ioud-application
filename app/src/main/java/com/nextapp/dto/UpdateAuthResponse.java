package com.nextapp.dto;

public class UpdateAuthResponse {
    private String Response;

    public String getResponse() {
        return Response;
    }
    public void setResponse(String response) {
        this.Response = response;
    }

    @Override
    public String toString() {
        return "UpdateAuthResponse{" +
                "response='" + Response + '\'' +
                '}';
    }
}
