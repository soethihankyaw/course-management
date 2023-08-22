package com.spring.backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.backend.models.Course;
import com.spring.backend.models.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Integer>{
	

}
