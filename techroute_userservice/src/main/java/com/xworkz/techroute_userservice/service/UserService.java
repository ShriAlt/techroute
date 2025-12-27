package com.xworkz.techroute_userservice.service;

import com.xworkz.techroute_userservice.dto.RegisterRequest;
import com.xworkz.techroute_userservice.dto.UserResponse;

public interface UserService {

    UserResponse registerUser(RegisterRequest registerRequest);

}
