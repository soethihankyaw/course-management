package com.spring.backend.service;

import java.util.List;

import com.spring.backend.models.dto.CourseDto;

public interface CourseService {
	
	CourseDto createCourse(int teacherId, CourseDto courseDto);
	
	CourseDto getCourseById(int id);
	
	CourseDto updateCourse(int teacherId, int courseId, CourseDto courseDto);
	
	void deletCourse(int id);
}
