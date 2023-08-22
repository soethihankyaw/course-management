package com.spring.backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.backend.models.Course;

public interface CourseRepository extends JpaRepository<Course, Integer>{
	
	public Page<Course> findAllByTeacherId(int teacherId, Pageable pageable);
}
