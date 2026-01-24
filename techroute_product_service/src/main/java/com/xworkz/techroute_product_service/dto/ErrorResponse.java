package com.xworkz.techroute_product_service.dto;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;


public record ErrorResponse (
     HttpStatus httpStatus,
     String message,
     String path,
     LocalDateTime timestamp
){}