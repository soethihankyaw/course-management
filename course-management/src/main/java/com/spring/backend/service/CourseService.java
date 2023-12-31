package com.spring.backend.service;

import java.util.List;

import com.spring.backend.models.dto.CourseDto;
import com.spring.backend.models.dto.CourseResponse;

public interface CourseService {
	
	CourseDto createCourse(int teacherId, CourseDto courseDto);
	
	CourseDto getCourseById(int id);
	
	CourseDto updateCourse(int teacherId, int courseId, CourseDto courseDto);
	
	void deletCourse(int id);
	
	CourseResponse getAllCourses(int pageNo, int pageSize);
	
	CourseResponse customSearch(int pageNo, int pageSize, String courseName,
			String description, Integer duration, Double courseFees);
}
