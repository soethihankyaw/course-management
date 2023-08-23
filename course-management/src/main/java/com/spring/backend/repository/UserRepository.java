package com.spring.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.backend.models.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer>{
	
	Optional<UserEntity> findByUsername(String username);
	Boolean existsByUsername(String username);
}
