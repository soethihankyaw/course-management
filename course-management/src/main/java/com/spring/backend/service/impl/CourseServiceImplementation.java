package com.spring.backend.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.spring.backend.exception.CourseNotFoundException;
import com.spring.backend.exception.TeacherNotFoundException;
import com.spring.backend.models.dto.CourseDto;
import com.spring.backend.models.dto.CourseResponse;
import com.spring.backend.models.entity.Course;
import com.spring.backend.models.entity.Teacher;
import com.spring.backend.repository.CourseRepository;
import com.spring.backend.repository.TeacherRepository;
import com.spring.backend.service.CourseService;

@Service
public class CourseServiceImplementation implements CourseService{
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	//create course
	@Override
	public CourseDto createCourse(int teacherId, CourseDto courseDto) {
		Course course = mapToEntity(courseDto);
		
		Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(()-> new TeacherNotFoundException("Teacher not found"));	
		course.setTeacher(teacher);
		
		Course newCourse = courseRepository.save(course);
		return mapToDto(newCourse);
	}


	@Override
	public CourseDto getCourseById(int id) {
		
		return null;
	}
	
	//update Course
	@Override
	public CourseDto updateCourse(int teacherId, int courseId, CourseDto courseDto) {
		Course course = courseRepository.findById(courseId).orElseThrow(() -> new CourseNotFoundException("Course Not Found"));
		
		Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(() -> new TeacherNotFoundException("Teacher Not Found"));
		
		course.setCourseName(courseDto.getCourseName());
		course.setDescription(courseDto.getDescription());
		course.setDuration(courseDto.getDuration());
		course.setCourseFees(courseDto.getCourseFees());
		course.setTeacher(teacher);
		
		Course updatedCourse = courseRepository.save(course);
		
		return mapToDto(updatedCourse);
	}
	
	//delete Course
	@Override
	public void deletCourse(int id) {
		Course course = courseRepository.findById(id).orElseThrow(() -> new CourseNotFoundException("Course Not Found"));
		courseRepository.delete(course);
	}
	
	//entity to dto
	private CourseDto mapToDto(Course course) {
		CourseDto courseDto = new CourseDto();
		
		courseDto.setId(course.getId());
		courseDto.setCourseName(course.getCourseName());
		courseDto.setDescription(course.getDescription());
		courseDto.setCourseFees(course.getCourseFees());
		courseDto.setDuration(course.getDuration());
		
		return courseDto;	
	}
	
	//dto to entity
	private Course mapToEntity(CourseDto courseDto) {
		Course course = new Course();
		
		course.setId(courseDto.getId());
		course.setCourseName(courseDto.getCourseName());
		course.setDescription(courseDto.getDescription());
		course.setDuration(courseDto.getDuration());
		course.setCourseFees(courseDto.getCourseFees());
		
		return course;
		
	}

	//get all courses with pagination
	@Override
	public CourseResponse getAllCourses(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<Course> courses = courseRepository.findAll(pageable);
		List<Course> listOfCourse = courses.getContent();
		List<CourseDto> content = listOfCourse.stream()
										.map(c -> mapToDto(c)).collect(Collectors.toList());
		
		CourseResponse courseResponse = new CourseResponse();
		courseResponse.setContent(content);
		courseResponse.setPageNo(courses.getNumber());
		courseResponse.setPageSize(courses.getSize());
		courseResponse.setTotalElement(courses.getTotalElements());
		courseResponse.setTotalPages(courses.getTotalPages());
		courseResponse.setLast(courses.isLast());
		
		return courseResponse;
	}
	
	//filter for searching 
	@Override
	public CourseResponse customSearch(int pageNo, int pageSize, String courseName,
			String description, Integer duration, Double courseFees) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<Course> courses = searchCourse(courseName, description, duration, courseFees, pageable);
		List<Course> listOfCourse = courses.getContent();
		List<CourseDto> content = listOfCourse.stream()
										.map(c -> mapToDto(c)).collect(Collectors.toList());
		
		CourseResponse courseResponse = new CourseResponse();
		courseResponse.setContent(content);
		courseResponse.setPageNo(courses.getNumber());
		courseResponse.setPageSize(courses.getSize());
		courseResponse.setTotalElement(courses.getTotalElements());
		courseResponse.setTotalPages(courses.getTotalPages());
		courseResponse.setLast(courses.isLast());
		
		return courseResponse;
	}
	
	
	private Page<Course> searchCourse( String courseName,
			String description, Integer duration, Double courseFees, Pageable pageable) {
		Specification<Course> specification = (root, query, cb) -> {
			List<Predicate> predicates = new ArrayList<>();
			
			if(courseName != null) {
				predicates.add(cb.like(cb.lower(root.get("courseName")), "%" + courseName.toLowerCase() + "%"));
			}
			
			if(description != null ) {
				predicates.add(cb.like(cb.lower(root.get("description")), "%" + description.toLowerCase() + "%"));
			}
			
			if(duration != null) {
				predicates.add(cb.equal(root.get("duration"), duration));
			}
			
			if(courseFees != null) {
				predicates.add(cb.equal(root.get("courseFees"), courseFees));
			}
			
			return cb.and(predicates.toArray(new Predicate[0]));
		};
		
		return courseRepository.findAll(specification, pageable);
	}
}
