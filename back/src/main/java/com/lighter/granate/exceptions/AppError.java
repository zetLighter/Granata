package com.lighter.granate.exceptions;

import lombok.Data;

import java.util.Date;

@Data
public class AppError {
    public int status;
    public String message;
    public Date timestamp;

    public AppError(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = new Date();
    }
}
