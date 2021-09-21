package com.baska.GateWay.Payload;

public class GetUserRequest {
    Long userId;

    public GetUserRequest(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public GetUserRequest() {
    }
}
