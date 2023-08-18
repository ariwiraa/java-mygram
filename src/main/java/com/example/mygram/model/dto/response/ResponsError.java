package com.example.mygram.model.dto.response;

import java.time.LocalDateTime;

public class ResponsError {
    private Integer code;
    private String messsage;
    private LocalDateTime timestamp;

    private Object error;

    public ResponsError(Integer code, String messsage, LocalDateTime timestamp, Object error) {
        this.code = code;
        this.messsage = messsage;
        this.timestamp = timestamp;
        this.error = error;
    }

    public Integer getCode() {
        return code;
    }

    public String getMesssage() {
        return messsage;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Object getError() {
        return error;
    }
}
