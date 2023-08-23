package com.spring.backend.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.spring.backend.models.entity.Course;
import com.spring.backend.models.entity.Teacher;
import com.spring.backend.models.dto.CourseDto;
import com.spring.backend.models.dto.CourseResponse;
import com.spring.backend.models.dto.TeacherDto;
import com.spring.backend.repository.CourseRepository;
import com.spring.backend.repository.TeacherRepository;
import com.spring.backend.service.TeacherService;

@Service
public class TeacherServiceImplementation implements TeacherService{

	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	//create teacher
	@Override
	public TeacherDto createTeacher(TeacherDto teacherDto) {
		Teacher teacher = new Teacher();
		teacher.setName(teacherDto.getName());
		teacher.setEmail(teacherDto.getEmail());
		teacher.setPhone(teacherDto.getPhone());
		
		Teacher newTeacher = teacherRepository.save(teacher);
		
		TeacherDto newTeacherDto = new TeacherDto();
		newTeacherDto.setId(newTeacher.getId());
		newTeacherDto.setName(newTeacher.getName());
		newTeacherDto.setEmail(newTeacher.getEmail());
		newTeacherDto.setPhone(newTeacher.getPhone());
		
		return newTeacherDto;
	}
	
	//entity to dto
	private TeacherDto mapToDto(Teacher teacher) {
		
		TeacherDto teacherDto = new TeacherDto();
		teacherDto.setId(teacher.getId());
		teacherDto.setName(teacher.getName());
		teacherDto.setEmail(teacher.getEmail());
		teacherDto.setPhone(teacher.getPhone());
		return teacherDto;
	}
	
	//dto to entity
	private Teacher mapToEntity(TeacherDto teacherDto) {
		
		Teacher teacher = new Teacher();
		teacher.setId(teacherDto.getId());
		teacher.setName(teacherDto.getName());
		teacher.setEmail(teacherDto.getEmail());
		teacher.setPhone(teacherDto.getPhone());
		
		return teacher;
	}
	
	private CourseDto mapToCourseDto(Course course) {
		CourseDto courseDto = new CourseDto();
		
		courseDto.setId(course.getId());
		courseDto.setCourseName(course.getCourseName());
		courseDto.setDescription(course.getDescription());
		courseDto.setCourseFees(course.getCourseFees());
		courseDto.setDuration(course.getDuration());
		
		return courseDto;	
	}

	@Override
	public CourseResponse getAllCourses(int teacherId, int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<Course> courses = courseRepository.findAllByTeacherId(teacherId, pageable);
		List<Course> listOfCourse = courses.getContent();
		List<CourseDto> content = listOfCourse.stream()
									.map(c -> mapToCourseDto(c)).collect(Collectors.toList());
		CourseResponse courseResponse = new CourseResponse();
		courseResponse.setContent(content);
		courseResponse.setPageNo(courses.getNumber());
		courseResponse.setPageSize(courses.getSize());
		courseResponse.setTotalElement(courses.getTotalElements());
		courseResponse.setTotalPages(courses.getTotalPages());
		courseResponse.setLast(courses.isLast());
		
		return courseResponse;
	}
	
}
