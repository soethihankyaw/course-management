package com.spring.backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring.backend.models.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Integer>, JpaSpecificationExecutor<Course>{
	
	 Page<Course> findAllByTeacherId(int teacherId, Pageable pageable);
	
//	Page<Course> searchCourse(String courseName, Pageable pageable);
}
