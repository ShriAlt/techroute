package com.xworkz.techroute_userservice.dto;

import com.xworkz.techroute_userservice.enums.Status;
import lombok.Data;

@Data
public class StatusUpdateRequest {
    Status status;
}
