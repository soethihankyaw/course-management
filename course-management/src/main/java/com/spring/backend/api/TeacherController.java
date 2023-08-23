package com.spring.backend.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.backend.models.dto.CourseDto;
import com.spring.backend.models.dto.CourseResponse;
import com.spring.backend.models.dto.TeacherDto;
import com.spring.backend.models.entity.Course;
import com.spring.backend.service.CourseService;
import com.spring.backend.service.TeacherService;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private CourseService courseService;
	
	//create teacher
	@PostMapping("/create")
	public ResponseEntity<TeacherDto> createTeacher(@RequestBody TeacherDto teacherDto) {
		return new ResponseEntity<>(teacherService.createTeacher(teacherDto), HttpStatus.CREATED);
	}
	
	//create course
	@PostMapping("/{teacherId}/course/create")
    public ResponseEntity<CourseDto> createCourse(@PathVariable(value = "teacherId") int teacherId, @RequestBody CourseDto courseDto) {
        return new ResponseEntity<>(courseService.createCourse(teacherId, courseDto), HttpStatus.CREATED);
    }
	
	//update course
	@PutMapping("/{teacherId}/course/update/{id}")
	public ResponseEntity<CourseDto> updateCourse(@PathVariable(value="teacherId")int teacherId,@PathVariable(value="id") int courseId, @RequestBody CourseDto courseDto) {
		CourseDto updateCourse = courseService.updateCourse(teacherId, courseId , courseDto);
		return new ResponseEntity<>(updateCourse, HttpStatus.OK);
	}
	
	//delete course
	@DeleteMapping("/course/delete/{id}")
	public ResponseEntity<String> deleteCourse(@PathVariable int id) {
		courseService.deletCourse(id);
		return new ResponseEntity<>("Course deleted successfully.", HttpStatus.OK);
	}
	
	//get all courses with pagination by teacherId
	@GetMapping("/{id}/courses")
    public ResponseEntity<CourseResponse> getAllCourses(
    		@PathVariable int id,
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize
    ) {
        return new ResponseEntity<>(teacherService.getAllCourses(id, pageNo, pageSize), HttpStatus.OK);
    }
	
	
}	