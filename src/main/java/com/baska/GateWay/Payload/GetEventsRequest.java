package com.baska.GateWay.Payload;



public class GetEventsRequest {
    String dateBegin;
    String dateEnd;
    Long userId;

    public GetEventsRequest(String dateBegin, String dateEnd, Long userId) {
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.userId = userId;
    }

    public GetEventsRequest() {
    }

    public String getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(String dateBegin) {
        this.dateBegin = dateBegin;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
