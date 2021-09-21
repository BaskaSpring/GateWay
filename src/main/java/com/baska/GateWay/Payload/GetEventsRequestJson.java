package com.baska.GateWay.Payload;

import java.time.Instant;

public class GetEventsRequestJson {
    Instant dateBegin;
    Instant dateEnd;
    Long userId;

    public GetEventsRequestJson(Instant dateBegin, Instant dateEnd, Long userId) {
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.userId = userId;
    }

    public GetEventsRequestJson() {
    }

    public Instant getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(Instant dateBegin) {
        this.dateBegin = dateBegin;
    }

    public Instant getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Instant dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
