package com.example.project1gem.exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomErrorDetail {


    private Date timestamp;
    private String message;
    private String errorDetails;
}
