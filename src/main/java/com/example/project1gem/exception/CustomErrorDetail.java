package com.example.project1gem.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class CustomErrorDetail {

    /**
     * This field is used to store time of error Occured.
     */
    private Date timestamp;
    /**
     * This field is used to store error message.
     */
    private String message;
    /**
     * This field is used to store error details.
     */
    private String errorDetails;
}
