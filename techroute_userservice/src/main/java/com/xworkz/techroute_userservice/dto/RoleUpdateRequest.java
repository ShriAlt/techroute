package com.xworkz.techroute_userservice.dto;

import com.xworkz.techroute_userservice.enums.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RoleUpdateRequest {

    @NotBlank
    Role role;
}
