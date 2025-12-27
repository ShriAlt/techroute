package com.xworkz.techroute_userservice.mapper;

import com.xworkz.techroute_userservice.dto.RegisterRequest;
import com.xworkz.techroute_userservice.enity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "",target = "")
    UserEntity registerRequestToUserEntity(RegisterRequest registerRequest);
}
