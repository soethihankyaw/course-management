package com.spring.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.backend.models.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Integer>{

}
