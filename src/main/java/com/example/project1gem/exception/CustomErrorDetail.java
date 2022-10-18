package com.example.project1gem.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class CustomErrorDetail {


    private Date timestamp;
    private String message;
    private String errorDetails;
}
