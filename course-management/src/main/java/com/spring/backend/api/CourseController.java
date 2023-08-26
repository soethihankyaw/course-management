package com.spring.backend.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.backend.models.dto.CourseResponse;
import com.spring.backend.service.CourseService;

@RestController
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	
	@GetMapping("/courses")
    public ResponseEntity<CourseResponse> getAllCourses(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize
    ) {
        return new ResponseEntity<>(courseService.getAllCourses(pageNo, pageSize), HttpStatus.OK);
    }
	
	@GetMapping("/courses/search")
    public ResponseEntity<CourseResponse> customSearch(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize,
            @RequestParam(required = false) String courseName,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Integer duration,
            @RequestParam(required = false) Double courseFees
    ) {
        return new ResponseEntity<>(courseService.customSearch(pageNo, pageSize, courseName,
        		description, duration, courseFees), HttpStatus.OK);
    }
}
