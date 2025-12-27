package com.xworkz.techroute_userservice.repository;

import com.xworkz.techroute_userservice.enity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

}
