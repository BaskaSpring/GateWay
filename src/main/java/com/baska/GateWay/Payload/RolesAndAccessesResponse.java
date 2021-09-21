package com.baska.GateWay.Payload;

public class RolesAndAccessesResponse {
    String role;
    String service;

    public RolesAndAccessesResponse(String role, String service) {
        this.role = role;
        this.service = service;
    }

    public RolesAndAccessesResponse() {
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }
}
