package com.baska.GateWay.Payload;

public class JwtAccessResponse {
    Boolean permission;
    Boolean expiredToken;
    Long userId;

    public JwtAccessResponse(Boolean permission, Boolean expiredToken, Long userId) {
        this.permission = permission;
        this.expiredToken = expiredToken;
        this.userId = userId;
    }

    public JwtAccessResponse() {
    }

    public Boolean getPermission() {
        return permission;
    }

    public void setPermission(Boolean permission) {
        this.permission = permission;
    }

    public Boolean getExpiredToken() {
        return expiredToken;
    }

    public void setExpiredToken(Boolean expiredToken) {
        this.expiredToken = expiredToken;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
