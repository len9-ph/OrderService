package com.lgadetsky.orderservice.exception;

import lombok.Data;

@Data
public class CustomErrorResponse {
    private int status;
    private String error;
}
