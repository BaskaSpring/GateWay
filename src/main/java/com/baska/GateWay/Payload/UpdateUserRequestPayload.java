package com.baska.GateWay.Payload;


import java.util.List;

public class UpdateUserRequestPayload {

    Long userId;
    String userName;
    String email;
    String status;
    String newPassword;
    String blockedTime;
    Integer countWrongPassword;
    List<RolesAndAccessesResponse> rolesAndAccessesResponses;

    public UpdateUserRequestPayload(Long userId, String userName, String email, String status, String newPassword, String blockedTime, Integer countWrongPassword, List<RolesAndAccessesResponse> rolesAndAccessesResponses) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.status = status;
        this.newPassword = newPassword;
        this.blockedTime = blockedTime;
        this.countWrongPassword = countWrongPassword;
        this.rolesAndAccessesResponses = rolesAndAccessesResponses;
    }

    public UpdateUserRequestPayload() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getBlockedTime() {
        return blockedTime;
    }

    public void setBlockedTime(String blockedTime) {
        this.blockedTime = blockedTime;
    }

    public Integer getCountWrongPassword() {
        return countWrongPassword;
    }

    public void setCountWrongPassword(Integer countWrongPassword) {
        this.countWrongPassword = countWrongPassword;
    }

    public List<RolesAndAccessesResponse> getRolesAndAccessesResponses() {
        return rolesAndAccessesResponses;
    }

    public void setRolesAndAccessesResponses(List<RolesAndAccessesResponse> rolesAndAccessesResponses) {
        this.rolesAndAccessesResponses = rolesAndAccessesResponses;
    }
}
