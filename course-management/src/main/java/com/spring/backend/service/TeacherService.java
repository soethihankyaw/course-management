package com.spring.backend.service;

import com.spring.backend.models.dto.CourseResponse;
import com.spring.backend.models.dto.TeacherDto;

public interface TeacherService {
	
	TeacherDto createTeacher(TeacherDto teacherDto);
	
	CourseResponse getAllCourses(int teacherId, int pageNo, int pageSize);
}