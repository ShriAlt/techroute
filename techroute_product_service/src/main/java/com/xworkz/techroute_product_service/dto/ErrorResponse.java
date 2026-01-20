package com.xworkz.techroute_product_service.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;


public record ErrorResponse (

     HttpStatus httpStatus,
     String message,
     String path,
     LocalDateTime timestamp

//    public ErrorResponse(HttpStatus httpStatus, String message, String path) {
//        this();
//        this.httpStatus = httpStatus;
//        this.message = message;
//        this.path = path;
//    }
){
//    public ErrorResponse(){
//        this.timestamp= LocalDateTime.now();
//    }
}