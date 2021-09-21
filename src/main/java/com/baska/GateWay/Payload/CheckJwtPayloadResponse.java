package com.baska.GateWay.Payload;

public class CheckJwtPayloadResponse {
    Boolean valid;
    Boolean expired;

    public CheckJwtPayloadResponse(Boolean valid, Boolean expired) {
        this.valid = valid;
        this.expired = expired;
    }

    public CheckJwtPayloadResponse() {
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public Boolean getExpired() {
        return expired;
    }

    public void setExpired(Boolean expired) {
        this.expired = expired;
    }
}
