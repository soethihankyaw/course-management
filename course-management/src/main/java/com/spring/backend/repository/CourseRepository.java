package com.spring.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.backend.models.Course;

public interface CourseRepository extends JpaRepository<Course, Integer>{

}
